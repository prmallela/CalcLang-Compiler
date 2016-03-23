import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.misc.Nullable;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.gui.SystemFontMetrics;

import java.io.InputStreamReader;
import java.util.HashMap;

public class TypeCheckingVisitor extends CalcLangBaseVisitor<Type> {

    private HashMap<String, Type> symbols = new HashMap<>();
    private CommonTokenStream tokens;
    private final boolean TRACE = true;
    public int errors = 0;
    String ne;

    public TypeCheckingVisitor(CommonTokenStream tokens) {
        this.tokens = tokens;
    }

    @Override
    public Type visitIntExpr(CalcLangParser.IntExprContext ctx) {
        return trace(ctx, Type.INT);
    }

    @Override
    public Type visitBoolExpr(CalcLangParser.BoolExprContext ctx) {
        //System.out.println(ctx.bool().getText());
        //if(ne==null)
            return trace(ctx,Type.BOOL);
       // if(ne.equals(String.valueOf('!')))
      //  return trace(ctx, Type.BOOL);
      //  else
        //    return trace(ctx,Type.ERROR);
    }


    @Override
    public Type visitFloatExpr(CalcLangParser.FloatExprContext ctx) {
        return trace(ctx, Type.FLOAT);
    }

    @Override
    public Type visitIfExpr(CalcLangParser.IfExprContext ctx){
        try {
            String b = ctx.expr(0).getText();
            String th = ctx.expr(1).getText();
            //int th1 = Integer.parseInt(th);
            String el=ctx.expr(2).getText();
            //int el1 = Integer.parseInt(el);
            if(b.equals("true")||b.equals("false")){
                if((th.equals("true") && el.equals("false"))
                        ||(el.equals("true") && th.equals("false"))  ){
                    return trace(ctx,Type.BOOL);
                }
                else if (isNumeric(th) && isNumeric(el)){
                    return trace(ctx,Type.BOOL);
                }
                else
                    return typeMismatch(ctx,Type.BOOL, Type.INT);

            }
            else
                return typeMismatch(ctx,Type.BOOL, Type.INT);

        }
        catch (Exception x){
            return typeMismatch(ctx,Type.BOOL, Type.INT);
        }
    }

    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
    @Override
    public Type visitOpExpr(CalcLangParser.OpExprContext ctx) {
        Type leftType = ctx.left.accept(this);
        Type rightType = ctx.right.accept(this);
        if (ctx.op.getText() != null && leftType == Type.BOOL && rightType == Type.BOOL) {
            switch (ctx.op.getText()) {
                case "-":case "^":case "*":case "+":
                    return typeMismatch(ctx, leftType, rightType);
                default:
                    return trace(ctx, leftType);
            }
        }
        else if ((leftType ==Type.BOOL && rightType==Type.INT)
                || (leftType==Type.BOOL && rightType==Type.FLOAT)
                || (leftType==Type.INT && rightType==Type.BOOL)
                || (leftType==Type.FLOAT && rightType==Type.BOOL)){
                switch (ctx.op.getText()){
                    case "/":case "^":case "+":case"<":case">=":case">":case"<=":case"=":case"<>":
                    case "&":

                        return typeMismatch(ctx,leftType,rightType);
                    default:
                        return trace(ctx,leftType);
                }
        }
        else if ((leftType == Type.INT || leftType == Type.FLOAT) &&
                (rightType == Type.INT || rightType == Type.FLOAT)) {
            switch (ctx.op.getText()) {
                case "^":
                case "*":
                case "/":
                case "+":
                case "-":
               // case "&":
                    if ((leftType == Type.INT || leftType == Type.FLOAT) &&
                            (rightType == Type.INT || rightType == Type.FLOAT))
                        return trace(ctx, leastUpperBound(leftType, rightType));

                    else {
                        return typeMismatch(ctx, leftType, rightType);
                    }
                case "|":case "&":
                    return typeMismatch(ctx,leftType,rightType);
                default:
                    return trace(ctx, leftType);
            }
        }
        return trace(ctx,leftType);
        }


    public static Type leastUpperBound(Type t1, Type t2)
    {
        if(t1==t2) return t1;
        if (t1 == Type.INT && t2 == Type.FLOAT) return Type.FLOAT;
        return null;
    }

    @Override
    public Type visitParenExpr(CalcLangParser.ParenExprContext ctx) {
        return trace(ctx, ctx.expr().accept(this));
    }

    @Override
    public Type visitNegExpr(CalcLangParser.NegExprContext ctx) {
        ne= ctx.op.getText().toString();
        Type t=ctx.expr().accept(this);
        switch(ctx.op.getText()){
            case "-":
                if(t==Type.INT|| t== Type.FLOAT){
                    return trace(ctx,t);
                }
                else{
                    return typeMismatch(ctx,t,Type.FLOAT);
                }
            case "!":
                if(t==Type.BOOL){
                    return trace(ctx,t);
                }
                else{
                    return typeMismatch(ctx,t,Type.BOOL);
                }
            default:
                throw new UnsupportedOperationException(ctx.op.getText());
        }
    }

    @Override
    public Type visitFunExpr(CalcLangParser.FunExprContext ctx) {
        String name = ctx.ID().getText();
        switch(name) {
            case "float":  // INT → FLOAT
                if(checkNumArgs(ctx, name, 1, ctx.expr().size())) {
                    Type type = ctx.expr(0).accept(this);
                    if(type == Type.INT) return trace(ctx, Type.FLOAT);
                    else return typeMismatch(ctx, type, Type.INT);
                }
                break;
            case "floor":  // FLOAT → INT
                if(checkNumArgs(ctx, name, 1, ctx.expr().size())) {
                    Type type = ctx.expr(0).accept(this);
                    if(type == Type.FLOAT ||type==Type.INT) return trace(ctx,type);
                    else return typeMismatch(ctx, type, Type.FLOAT);
                }
            case "log":
                if(checkNumArgs(ctx, name, 2, ctx.expr().size())) {
                    Type type = ctx.expr(0).accept(this);
                    if(type==Type.INT ||type==Type.FLOAT) return trace(ctx,type);
                    else return typeMismatch(ctx,type,Type.FLOAT);
                }
            case "sqrt":
                if(checkNumArgs(ctx, name, 1, ctx.expr().size())) {
                    Type type = ctx.expr(0).accept(this);
                    if(type==Type.INT ||type==Type.FLOAT) return trace(ctx,type);
                    else return typeMismatch(ctx,type,Type.FLOAT);
                }
                    default:
                error(ctx, "undefined function: " + name);
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
    public Type visitAssignStmt(CalcLangParser.AssignStmtContext ctx) {
        String var = ctx.ID().getText();
        Type type = ctx.expr().accept(this);
        symbols.put(var, type);
        return null;
    }

    private Type trace(ParseTree ctx, Type type) {
        if(TRACE) {
            System.err.printf("%s ⊢ %s : %s\n", symbols, ctx.getText(), type);
        }
        return type;
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
        errors++;
        Interval interval = ctx.getSourceInterval();
        Token first = tokens.get(interval.a);
        Token last = tokens.get(interval.b);
        if(first == last) {
            System.err.printf("%d.%d",
                    first.getLine(),
                    first.getCharPositionInLine()
            );
        } else {
            System.err.printf("%d.%d-%d.%d",
                    first.getLine(),
                    first.getCharPositionInLine(),
                    last.getLine(),
                    last.getCharPositionInLine()
            );
        }
        System.err.printf(": Error: %s\n", message);
    }
}
