import cal.CalcLangBaseVisitor;
import calc.grammar.CalcLangParser;
import org.antlr.v4.runtime.ANTLRInputStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

class ConvertToIR extends CalcLangBaseVisitor<Address> {

    IRGraph graph = new IRGraph();
    private final TypeChecker typeChecker;
    private final SymbolTable<String> symbols = new SymbolTable<>();
    private static int nextMangleNum = 1;

    ConvertToIR(TypeChecker typeChecker) {
        this.typeChecker = typeChecker;
        this.graph = new IRGraph();
        this.typeChecker.getParseTree().accept(this);
    }

    @Override
    public Address visitTop(CalcLangParser.TopContext ctx) {
        super.visitTop(ctx);
        graph.add(new Instruction(Instruction.Kind.END));
        return null;
    }

    @Override
    public Address visitIfStmt(CalcLangParser.IfStmtContext ctx) {
        Address condAddr = ctx.expr().accept(this);
        Address thenLabel = new Address();
        Address joinLabel = new Address();
        graph.add(new Instruction(Instruction.Kind.BRANCH,
                condAddr, joinLabel, thenLabel));
        graph.newBlock(thenLabel);
        ctx.stmt().accept(this);
        graph.add(new Instruction(Instruction.Kind.JUMP,
                joinLabel));
        graph.newBlock(joinLabel);
        return null;
    }

    @Override
    public Address visitWhileStmt(CalcLangParser.WhileStmtContext ctx) {
        Address condLabel = new Address(); // Must be able to jump back to condition
        graph.add(new Instruction(Instruction.Kind.JUMP, condLabel));
        graph.newBlock(condLabel);
        Address condAddr = ctx.expr().accept(this);
        Address bodyLabel = new Address();
        Address joinLabel = new Address();
        graph.add(new Instruction(Instruction.Kind.BRANCH,
                condAddr, joinLabel, bodyLabel));
        graph.newBlock(bodyLabel);
        ctx.stmt().accept(this);
        graph.add(new Instruction(Instruction.Kind.JUMP, condLabel));
        graph.newBlock(joinLabel);
        return null;
    }

    @Override
    public Address visitIfElseStmt(CalcLangParser.IfElseStmtContext ctx) {
        Address condAddr = ctx.expr().accept(this);
        Address thenLabel = new Address();
        Address elseLabel = new Address();
        Address joinLabel = new Address();
        graph.add(new Instruction(Instruction.Kind.BRANCH,
                condAddr, elseLabel, thenLabel));
        graph.newBlock(thenLabel);
        ctx.stmt(0).accept(this);
        graph.add(new Instruction(Instruction.Kind.JUMP, joinLabel));
        graph.newBlock(elseLabel);
        ctx.stmt(1).accept(this);
        graph.add(new Instruction(Instruction.Kind.JUMP, joinLabel));
        graph.newBlock(joinLabel);
        return null;
    }

    @Override
    public Address visitBlockStmt(CalcLangParser.BlockStmtContext ctx) {
        symbols.enter();
        super.visitBlockStmt(ctx);
        symbols.leave();
        return null;
    }

    @Override
    public Address visitBoolExpr(CalcLangParser.BoolExprContext ctx) {
        return new Address(Address.Kind.CONST, Type.BOOL,
                ctx.bool().getText().equals("true")? "1" : "0");
    }

    @Override
    public Address visitStringExpr(CalcLangParser.StringExprContext ctx) {
        String rawString = ctx.STR().getText();
        return new Address(Address.Kind.CONST, Type.STRING,
                rawString.substring(1, rawString.length()-1));
    }

    @Override
    public Address visitIntExpr(CalcLangParser.IntExprContext ctx) {
        return new Address(Address.Kind.CONST, Type.INT,
                ctx.INT().getText());
    }

    @Override
    public Address visitFloatExpr(CalcLangParser.FloatExprContext ctx) {
        return new Address(Address.Kind.CONST, Type.FLOAT,
                ctx.FLOAT().getText());
    }

    private Address maybeCoerce(CalcLangParser.ExprContext ctx) {
        Address a = ctx.accept(this);
        Type coerce = typeChecker.getCoercion(ctx);
        if(coerce != null) {
            Address a2 = new Address(coerce);
            assert a.type == Type.INT;
            assert a2.type == Type.FLOAT;
            graph.add(new Instruction(Instruction.Kind.I2F, a2, a));
            a = a2;
        }
        return a;
    }

    @Override
    public Address visitOpExpr(CalcLangParser.OpExprContext ctx) {
        Address left = maybeCoerce(ctx.left);
        Address right = maybeCoerce(ctx.right);
        Address result = new Address(typeChecker.getNodeType(ctx));
        Instruction.Kind k;
        Address tmp;
        switch(ctx.op.getText()) {
            case "+":
                if(left.type == Type.STRING) {
                    k = Instruction.Kind.SCONCAT;
                } else {
                    k = Instruction.Kind.ADD;
                }
                break;
            case "-":  k = Instruction.Kind.SUB; break;
            case "*":  k = Instruction.Kind.MUL; break;
            case "/":  k = Instruction.Kind.DIV; break;
            case "%":  k = Instruction.Kind.MOD; break;
            case "^":  k = Instruction.Kind.POW; break;
            case "=":  k = Instruction.Kind.EQ;  break;
            case "&":  k = Instruction.Kind.MUL; break; // boolean AND is multiply
            case "|":  k = Instruction.Kind.OR;  break;
            case "<>": k = Instruction.Kind.NE;  break;
            case "<":  k = Instruction.Kind.LT;  break;
            case "<=": k = Instruction.Kind.LE;  break;
            case ">": // Compile GT as LT with left/right swapped
                k = Instruction.Kind.LT;
                tmp = left; left = right; right = tmp;
                break;
            case ">=": // Compile GT as LT with left/right swapped
                k = Instruction.Kind.LE;
                tmp = left; left = right; right = tmp;
                break;
            default:
                throw new UnsupportedOperationException(ctx.op.getText());
        }
        graph.add(new Instruction(k, result, left, right));
        return result;
    }

    @Override
    public Address visitParenExpr(CalcLangParser.ParenExprContext ctx) {
        return ctx.expr().accept(this);
    }

    @Override
    public Address visitNegExpr(CalcLangParser.NegExprContext ctx) {
        Address a = ctx.expr().accept(this);
        Address result = new Address(a.type);
        Instruction instr;
        String op = ctx.op.getText();
        switch(op) {
            case "-":
                // If it's a negated constant, prepend the minus sign
                if (a.kind == Address.Kind.CONST && !a.value.startsWith("-")) {
                    return new Address(Address.Kind.CONST, a.type, "-" + a.value);
                }
                // Otherwise calculate by subtracting from zero.
                Address z = new Address(Address.Kind.CONST, a.type, "0");
                instr = new Instruction(Instruction.Kind.SUB, result, z, a);
                break;
            case "!":
                // It's a boolean NOT, so subtract from 1 (true)
                Address t = new Address(Address.Kind.CONST, Type.BOOL, "1");
                instr = new Instruction(Instruction.Kind.SUB, result, t, a);
                break;
            default:
                throw new UnsupportedOperationException(op);
        }
        graph.add(instr);
        return result;
    }

    @Override
    public Address visitVarExpr(CalcLangParser.VarExprContext ctx) {
        String var = ctx.ID().getText();
        String mangled = symbols.get(var);
        return new Address(Address.Kind.VAR,
                typeChecker.getNodeType(ctx),
                mangled);
    }

    @Override
    public Address visitAssignStmt(CalcLangParser.AssignStmtContext ctx) {
        Address right = maybeCoerce(ctx.expr());
        String var = ctx.ID().getText();
        String mangled = symbols.get(var);
        Address left = new Address(Address.Kind.VAR,
                typeChecker.getNodeType(ctx),
                mangled);
        graph.add(new Instruction(Instruction.Kind.STORE, left, right));
        return null;
    }

    @Override
    public Address visitVarStmt(CalcLangParser.VarStmtContext ctx) {
        Address right = ctx.expr().accept(this);
        String var = ctx.ID().getText();
        String mangled = var + nextMangleNum++;
        symbols.put(var, mangled);
        Address left = new Address(Address.Kind.VAR,
                right.type,
                mangled);
        graph.add(new Instruction(Instruction.Kind.STORE, left, right));
        return null;
    }

    @Override
    public Address visitPrintStmt(CalcLangParser.PrintStmtContext ctx) {
        Address e = ctx.expr().accept(this);
        graph.add(new Instruction(Instruction.Kind.PRINT, e));
        return null;
    }

    @Override
    public Address visitFunExpr(CalcLangParser.FunExprContext ctx) {
        String funcName = ctx.ID().getText();
        ArrayList<Address> addresses = new ArrayList<>();
        for(CalcLangParser.ExprContext e : ctx.expr()) {
            addresses.add(maybeCoerce(e));
        }
        Instruction.Kind kind;
        switch(funcName) {
            case "floor": kind = Instruction.Kind.FLOOR; break;
            case "sqrt":  kind = Instruction.Kind.SQRT; break;
            case "log":   kind = Instruction.Kind.LOG; break;
            case "readLine": kind = Instruction.Kind.READLINE; break;
            case "random": kind = Instruction.Kind.RANDOM; break;
            case "parseInt": kind = Instruction.Kind.S2I; break;
            case "showInt": kind = Instruction.Kind.I2S; break;
            default:
                throw new UnsupportedOperationException(funcName);
        }
        Address result = new Address(typeChecker.getNodeType(ctx));
        Instruction instr;
        switch (addresses.size()) {
            case 0:
                instr = new Instruction(kind, result);
                break;
            case 1:
                instr = new Instruction(kind, result,
                        addresses.get(0));
                break;
            case 2:
                instr = new Instruction(kind ,result,
                        addresses.get(0),
                        addresses.get(1));
                break;
            default:
                throw new UnsupportedOperationException(kind.toString());
        }
        graph.add(instr);
        return result;
    }

    public static void main(String[] args) throws IOException {
        InputStream is;
        if(args.length >= 1) {
            is = new FileInputStream(args[0]);
        }
        else {
            is = ConvertToIR.class.getResourceAsStream("guess.calc");
        }
        TypeChecker tc = new TypeChecker(new ANTLRInputStream(is));
        if(tc.getNumErrors() == 0) {
            ConvertToIR ir = new ConvertToIR(tc);
            System.out.println(ir.graph);
            new InterpretIR(ir.graph, null);
        }
    }
}
