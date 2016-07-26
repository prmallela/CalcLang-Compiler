import org.antlr.v4.runtime.ANTLRInputStream;
import org.junit.Test;

import static calc.grammar.CalcLangParser.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class TestNodeTypes {

    private ExprContext extractPrintExpr(TypeChecker checker) {
        TopContext top = (TopContext) checker.getParseTree();
        PrintStmtContext printStmt = (PrintStmtContext) top.prog().stmt(0);
        return printStmt.expr();
    }

    public void testCoerceOp(String op) {
        { // Left
            String code = "print 2 " + op + " 3.1;";
            ANTLRInputStream input = new ANTLRInputStream(code);
            TypeChecker checker = new TypeChecker(input);
            TestUtils.assertWellTyped(checker);
            OpExprContext e = (OpExprContext) extractPrintExpr(checker);
            assertEquals(Type.INT, checker.getNodeType(e.expr(0)));
            assertEquals(Type.FLOAT, checker.getCoercion(e.expr(0)));
            assertEquals(Type.FLOAT, checker.getNodeType(e.expr(1)));
            assertNull(checker.getCoercion(e.expr(1)));
        }
        { // Right
            String code = "print 3.1 " + op + " 2;";
            ANTLRInputStream input = new ANTLRInputStream(code);
            TypeChecker checker = new TypeChecker(input);
            TestUtils.assertWellTyped(checker);
            OpExprContext e = (OpExprContext) extractPrintExpr(checker);
            assertEquals(Type.INT, checker.getNodeType(e.expr(1)));
            assertEquals(Type.FLOAT, checker.getCoercion(e.expr(1)));
            assertEquals(Type.FLOAT, checker.getNodeType(e.expr(0)));
            assertNull(checker.getCoercion(e.expr(0)));
        }
    }

    @Test
    public void testCoerceArith() {
        testCoerceOp("+");
    }

    @Test
    public void testCoerceEq() {
        testCoerceOp("=");
    }

    @Test
    public void testCoerceRelational() {
        testCoerceOp("<");
    }

    @Test
    public void testCoerceFunArg() {
        String code = "print sqrt(9);";
        ANTLRInputStream input = new ANTLRInputStream(code);
        TypeChecker checker = new TypeChecker(input);
        TestUtils.assertWellTyped(checker);
        FunExprContext e = (FunExprContext) extractPrintExpr(checker);
        assertEquals(Type.INT, checker.getNodeType(e.expr(0)));
        assertEquals(Type.FLOAT, checker.getCoercion(e.expr(0)));
    }
}
