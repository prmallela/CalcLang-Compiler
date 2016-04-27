import calc.grammar.CalcLangBaseVisitor;
import calc.grammar.CalcLangParser;

import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

/** Traverse the parse tree and emit new instructions into the
 * {@link BasicBlock}. YOUR CODE GOES HERE.
 */
public class ConvertToIR extends CalcLangBaseVisitor<Address> {

    BasicBlock block = new BasicBlock();
    TypeChecker typeChecker;

    /**
     * Run the converter on the parse tree after it has been type-checked.
     */
    public ConvertToIR(TypeChecker typeChecker) {
        this.typeChecker = typeChecker;
        this.typeChecker.getParseTree().accept(this);
    }

    /**
     * Run the converter on a program entered interactively
     * on standard input.
     */
    public static void main(String[] args) throws IOException {
        TypeChecker checker = TypeChecker.fromStdin();
        if(0 == checker.getNumErrors()) {
            new ConvertToIR(checker).showAndTell(System.out);
        }
    }

    /**
     * Display the IR program and its output on the given output stream.
     */
    public ArrayList<String> showAndTell(PrintStream out) {
        out.println(block);
        InterpretIR interp = new InterpretIR();
        ArrayList<String> output = new ArrayList<>();
        interp.run(block, output);
        out.println(output);
        return output;
    }

    @Override
    public Address visitIntExpr(CalcLangParser.IntExprContext ctx) {
        String s = ctx.INT().getText();
        Address a = new Address(Address.Kind.CONST, Type.INT, s);
        return a;
    }

    @Override
    public Address visitFloatExpr(CalcLangParser.FloatExprContext ctx) {
        String s = ctx.FLOAT().getText();
        Address a = new Address(Address.Kind.CONST, Type.FLOAT, s);
        return a;
    }

    private Address maybeCoerce(CalcLangParser.ExprContext ctx){
        Address a = ctx.accept(this);
        Type t = typeChecker.getCoercion(ctx);
        if (t != null){
            Address tmp = new Address(t);
            block.add(new Instruction(Instruction.Kind.I2F, tmp, a));
            a = tmp;
        }
        return a;
    }
    @Override
    public Address visitOpExpr(CalcLangParser.OpExprContext ctx) {
        // ASSUME this represents addition.
        Type t = typeChecker.getNodeType(ctx);
        Address left = maybeCoerce(ctx.left);
        Address right = maybeCoerce(ctx.right);
        Address a = new Address(t);
        Instruction.Kind kind;
        Address tmp;
        String op = ctx.op.getText();
        switch (op){
            case "+":
                if(left.type == Type.STRING) {
                    kind = Instruction.Kind.SCONCAT;
                } else {
                    kind = Instruction.Kind.ADD;
                }
                break;
            case "-":  kind = Instruction.Kind.SUB; break;
            case "*":  kind = Instruction.Kind.MUL; break;
            case "/":  kind = Instruction.Kind.DIV; break;
            case "%":  kind = Instruction.Kind.MOD; break;
            case "^":  kind = Instruction.Kind.POW; break;
            case "=":  kind = Instruction.Kind.EQ;  break;
            case "&":  kind = Instruction.Kind.MUL; break;
            case "|":  kind = Instruction.Kind.OR;  break;
            case "<>": kind = Instruction.Kind.NE;  break;
            case "<":  kind = Instruction.Kind.LT;  break;
            case "<=": kind = Instruction.Kind.LE;  break;
            case ">":
                kind = Instruction.Kind.LT;
                tmp = left; left = right; right = tmp;
                break;
            case ">=":
                kind = Instruction.Kind.LE;
                tmp = left; left = right; right = tmp;
                break;
            default:
                throw new UnsupportedOperationException(op);
        }
        block.add(new Instruction(kind, a, left, right));
        return a;
    }

    /**
     * Translate a print statement node to the IR.
     */
    @Override
    public Address visitPrintStmt(CalcLangParser.PrintStmtContext ctx) {
        // Recursively visit the expression to be printed
        Address a = ctx.expr().accept(this);
        // TODO: This is a placeholder, replace with your own code.
        block.add(new Instruction(Instruction.Kind.PRINT, null, a));

//                new Address(Address.Kind.CONST, Type.STRING, "TODO")
        //));
        return null;
    }

    // TODO: Override other visitor methods here.
}
