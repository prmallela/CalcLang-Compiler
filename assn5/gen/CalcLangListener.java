// Generated from D:/GitLab/cs664s16/assn5/src/main/antlr\CalcLang.g4 by ANTLR 4.5.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CalcLangParser}.
 */
public interface CalcLangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CalcLangParser#top}.
	 * @param ctx the parse tree
	 */
	void enterTop(CalcLangParser.TopContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalcLangParser#top}.
	 * @param ctx the parse tree
	 */
	void exitTop(CalcLangParser.TopContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalcLangParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(CalcLangParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalcLangParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(CalcLangParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignStmt}
	 * labeled alternative in {@link CalcLangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterAssignStmt(CalcLangParser.AssignStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignStmt}
	 * labeled alternative in {@link CalcLangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitAssignStmt(CalcLangParser.AssignStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrintStmt}
	 * labeled alternative in {@link CalcLangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterPrintStmt(CalcLangParser.PrintStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrintStmt}
	 * labeled alternative in {@link CalcLangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitPrintStmt(CalcLangParser.PrintStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BoolExpr}
	 * labeled alternative in {@link CalcLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBoolExpr(CalcLangParser.BoolExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BoolExpr}
	 * labeled alternative in {@link CalcLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBoolExpr(CalcLangParser.BoolExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IfExpr}
	 * labeled alternative in {@link CalcLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIfExpr(CalcLangParser.IfExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IfExpr}
	 * labeled alternative in {@link CalcLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIfExpr(CalcLangParser.IfExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FloatExpr}
	 * labeled alternative in {@link CalcLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFloatExpr(CalcLangParser.FloatExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FloatExpr}
	 * labeled alternative in {@link CalcLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFloatExpr(CalcLangParser.FloatExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NegExpr}
	 * labeled alternative in {@link CalcLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNegExpr(CalcLangParser.NegExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NegExpr}
	 * labeled alternative in {@link CalcLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNegExpr(CalcLangParser.NegExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VarExpr}
	 * labeled alternative in {@link CalcLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterVarExpr(CalcLangParser.VarExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VarExpr}
	 * labeled alternative in {@link CalcLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitVarExpr(CalcLangParser.VarExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IntExpr}
	 * labeled alternative in {@link CalcLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIntExpr(CalcLangParser.IntExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IntExpr}
	 * labeled alternative in {@link CalcLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIntExpr(CalcLangParser.IntExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ParenExpr}
	 * labeled alternative in {@link CalcLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParenExpr(CalcLangParser.ParenExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ParenExpr}
	 * labeled alternative in {@link CalcLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParenExpr(CalcLangParser.ParenExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code OpExpr}
	 * labeled alternative in {@link CalcLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterOpExpr(CalcLangParser.OpExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code OpExpr}
	 * labeled alternative in {@link CalcLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitOpExpr(CalcLangParser.OpExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FunExpr}
	 * labeled alternative in {@link CalcLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFunExpr(CalcLangParser.FunExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FunExpr}
	 * labeled alternative in {@link CalcLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFunExpr(CalcLangParser.FunExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalcLangParser#bool}.
	 * @param ctx the parse tree
	 */
	void enterBool(CalcLangParser.BoolContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalcLangParser#bool}.
	 * @param ctx the parse tree
	 */
	void exitBool(CalcLangParser.BoolContext ctx);
}