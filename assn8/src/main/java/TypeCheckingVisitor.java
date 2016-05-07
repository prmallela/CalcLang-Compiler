import calc.grammar.CalcLangBaseVisitor;
import calc.grammar.CalcLangParser;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.PrintStream;
import java.util.HashMap;

/**
 * Implements the typing rules of the Calc language, by traversing the
 * parse tree.
 */
class TypeCheckingVisitor extends CalcLangBaseVisitor<Type> {

    private final boolean TRACE = false;       // true logs each typing judgment
    private final CommonTokenStream tokens;    // used to report source locations
    private final PrintStream err = System.out;// where to write error messages

    private int numTypeErrors = 0;
    private final SymbolTable<Type> symbols = new SymbolTable<Type>();
    private final HashMap<ParseTree, Type> nodeType = new HashMap<>();
    private final HashMap<ParseTree, Type> coercion = new HashMap<>();

    private static final HashMap<String, Type[]> functions; // built-in function types

    static { // initialize function types
        functions = new HashMap<>();
        functions.put("floor",      new Type[]{Type.FLOAT, Type.INT});
        functions.put("sqrt",       new Type[]{Type.FLOAT, Type.FLOAT});
        functions.put("log",        new Type[]{Type.FLOAT, Type.FLOAT, Type.FLOAT});
        functions.put("random",     new Type[]{Type.FLOAT});
        functions.put("parseInt",   new Type[]{Type.STRING, Type.INT});
        functions.put("parseFloat", new Type[]{Type.STRING, Type.FLOAT});
        functions.put("readLine",   new Type[]{Type.STRING});
        functions.put("showInt",    new Type[]{Type.INT, Type.STRING});
        functions.put("showFloat",  new Type[]{Type.FLOAT, Type.STRING});
    }

    TypeCheckingVisitor(CommonTokenStream tokens) {
        this.tokens = tokens;
    }

    @Override
    public Type visitIntExpr(CalcLangParser.IntExprContext ctx) {
        return trace(ctx, Type.INT);
    }

    @Override
    public Type visitFloatExpr(CalcLangParser.FloatExprContext ctx) {
        return trace(ctx, Type.FLOAT);
    }

    @Override
    public Type visitStringExpr(CalcLangParser.StringExprContext ctx) {
        return trace(ctx, Type.STRING);
    }

    @Override
    public Type visitBoolExpr(CalcLangParser.BoolExprContext ctx) {
        return trace(ctx, Type.BOOL);
    }

    @Override
    public Type visitOpExpr(CalcLangParser.OpExprContext ctx) {
        Type leftType = ctx.left.accept(this);
        Type rightType = ctx.right.accept(this);
        // OpExpr is used for arithmetic, relational, equality, logical
        String op = ctx.op.getText();
        switch(op)
        {
        case "^":case "*":case "/":case "+":case "-": // arithmetic
        {
            if(leftType == Type.STRING && rightType == Type.STRING
                    && op.equals("+"))
            {
                return trace(ctx, Type.STRING);
            }
            if (!leftType.isNumeric()) {
                return typeMismatch(ctx, leftType, Type.FLOAT);
            }
            if (!rightType.isNumeric()) {
                return typeMismatch(ctx, rightType, Type.FLOAT);
            }
            Type resultType = unify(ctx.left, leftType, ctx.right, rightType);
            return trace(ctx, resultType);
        }

        case "<":case "<=":case ">":case ">=": // relational
        {
            if (!leftType.isNumeric()) {
                return typeMismatch(ctx, leftType, Type.FLOAT);
            }
            if (!rightType.isNumeric()) {
                return typeMismatch(ctx, rightType, Type.FLOAT);
            }
            unify(ctx.left, leftType, ctx.right, rightType);
            return trace(ctx, Type.BOOL);
        }
        case "&":case "|":  // logical
            if(leftType != Type.BOOL) {
                return typeMismatch(ctx, leftType, Type.BOOL);
            }
            if(rightType != Type.BOOL) {
                return typeMismatch(ctx, rightType, Type.BOOL);
            }
            return trace(ctx, Type.BOOL);

        case "=":case "<>":  // equality
        {
            Type resultType = unify(ctx.left, leftType, ctx.right, rightType);
            if(resultType != Type.ERROR) {
                return trace(ctx, Type.BOOL);
            }
            else {
                return typeMismatch(ctx, leftType, rightType);
            }
        }

        default:
            return trace(ctx, leftType);
        }
    }

    @Override
    public Type visitParenExpr(CalcLangParser.ParenExprContext ctx) {
        return trace(ctx, ctx.expr().accept(this));
    }

    @Override
    public Type visitNegExpr(CalcLangParser.NegExprContext ctx) {
        Type t = ctx.expr().accept(this); // type of subexpression
        switch(ctx.op.getText())
        {
            case "-":
                if(t == Type.INT || t == Type.FLOAT) {
                    return trace(ctx, t);
                }
                else {
                    return typeMismatch(ctx, t, Type.FLOAT);
                }
            case "!":
                if(t == Type.BOOL) {
                    return trace(ctx, t);
                }
                else {
                    return typeMismatch(ctx, t, Type.BOOL);
                }
            default:
                // impossible!
                throw new UnsupportedOperationException(ctx.op.getText());
        }
    }

    @Override
    public Type visitFunExpr(CalcLangParser.FunExprContext ctx) {
        String name = ctx.ID().getText();
        Type[] types = functions.get(name);
        if(types == null) {
            error(ctx, "undefined function: " + name);
            return trace(ctx, Type.ERROR);
        }
        int numArgs = types.length-1;
        if(checkNumArgs(ctx, name, numArgs, ctx.expr().size())) {
            for(int i = 0; i < numArgs; i++) {
                Type expected = types[i];
                Type actual = ctx.expr(i).accept(this);
                if(expected.leastUpperBound(actual) != expected) {
                    err.println("Error: In argument #"+(i+1)+" of "+name+":");
                    return typeMismatch(ctx, expected, actual);
                }
                if(expected != actual) {
                    coercion.put(ctx.expr(i), expected);
                }
            }
            return trace(ctx, types[numArgs]);
        }
        return trace(ctx, Type.ERROR);
    }

    @Override
    public Type visitVarExpr(CalcLangParser.VarExprContext ctx) {
        String var = ctx.ID().getText();
        Type type = symbols.get(var);
        if(type == null) {
            error(ctx, "undefined variable: " + var);
            return trace(ctx, Type.ERROR);
        }
        else {
            return trace(ctx, type);
        }
    }

    @Override
    public Type visitVarStmt(CalcLangParser.VarStmtContext ctx) {
        String var = ctx.ID().getText();
        Type type = ctx.expr().accept(this);
        symbols.put(var, type);
        return null;
    }

    @Override
    public Type visitAssignStmt(CalcLangParser.AssignStmtContext ctx) {
        String var = ctx.ID().getText();
        Type varType = symbols.get(var);
        if(varType == null) {
            error(ctx, "undefined variable: " + var);
            return trace(ctx, Type.ERROR);
        }
        else {
            Type exprType = ctx.expr().accept(this);
            Type newExprType = exprType.leastUpperBound(varType);
            if(newExprType != varType) {
                return typeMismatch(ctx, exprType, varType);
            }
            if(exprType != varType) {
                coercion.put(ctx.expr(), varType);
            }
            return trace(ctx, varType);
        }
    }

    @Override
    public Type visitBlockStmt(CalcLangParser.BlockStmtContext ctx) {
        symbols.enter();
        super.visitBlockStmt(ctx);
        symbols.leave();
        return null;
    }

    private Type trace(ParseTree ctx, Type type) {
        nodeType.put(ctx, type);
        if(TRACE) {
            err.printf("%s ⊢ %s : %s\n", symbols, ctx.getText(), type);
        }

        return type;
    }

    private Type unify(CalcLangParser.ExprContext e1,
                       Type t1,
                       CalcLangParser.ExprContext e2,
                       Type t2)
    {
        Type t0 = t1.leastUpperBound(t2);
        if(t0 != t1) {
            coercion.put(e1, t0);
        }
        if(t0 != t2) {
            coercion.put(e2, t0);
        }
        return t0;
    }

    private boolean checkNumArgs(ParseTree ctx, String name, int expected, int actual) {
        if(expected != actual) {
            error(ctx, String.format("%s arguments mismatch: expected %d, got %d",
                    name, expected, actual));
            return false;
        }
        else {
            return true;
        }
    }

    private Type typeMismatch(ParseTree ctx, Type t1, Type t2) {
        if(t1 != Type.ERROR && t2 != Type.ERROR) {
            error(ctx, String.format("type mismatch: %s vs %s in %s",
                    t1, t2, ctx.getText()));
        }
        return trace(ctx, Type.ERROR);
    }

    private void error(ParseTree ctx, String message) {
        numTypeErrors++;
        Interval interval = ctx.getSourceInterval();
        Token first = tokens.get(interval.a);
        Token last = tokens.get(interval.b);
        if(first == last) {
            err.printf("%d.%d",
                    first.getLine(),
                    first.getCharPositionInLine()
            );
        } else {
            err.printf("%d.%d-%d.%d",
                    first.getLine(),
                    first.getCharPositionInLine(),
                    last.getLine(),
                    last.getCharPositionInLine()
            );
        }
        err.printf(": Error: %s\n", message);
    }

    int getNumTypeErrors() {
        return numTypeErrors;
    }

    Type getNodeType(ParseTree ctx) {
        return nodeType.get(ctx);
    }

    Type getCoercion(ParseTree ctx) {
        return coercion.get(ctx);
    }
}
