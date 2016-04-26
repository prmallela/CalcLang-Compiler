// Generated from D:/GitLab/cs664s16/assn7/src/main/antlr\CalcLang.g4 by ANTLR 4.5.1
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
	 * Enter a parse tree produced by the {@code VarStmt}
	 * labeled alternative in {@link CalcLangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterVarStmt(CalcLangParser.VarStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VarStmt}
	 * labeled alternative in {@link CalcLangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitVarStmt(CalcLangParser.VarStmtContext ctx);
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
	 * Enter a parse tree produced by the {@code WhileStmt}
	 * labeled alternative in {@link CalcLangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterWhileStmt(CalcLangParser.WhileStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WhileStmt}
	 * labeled alternative in {@link CalcLangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitWhileStmt(CalcLangParser.WhileStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IfStmt}
	 * labeled alternative in {@link CalcLangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(CalcLangParser.IfStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IfStmt}
	 * labeled alternative in {@link CalcLangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(CalcLangParser.IfStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IfElseStmt}
	 * labeled alternative in {@link CalcLangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterIfElseStmt(CalcLangParser.IfElseStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IfElseStmt}
	 * labeled alternative in {@link CalcLangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitIfElseStmt(CalcLangParser.IfElseStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BlockStmt}
	 * labeled alternative in {@link CalcLangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterBlockStmt(CalcLangParser.BlockStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BlockStmt}
	 * labeled alternative in {@link CalcLangParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitBlockStmt(CalcLangParser.BlockStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StringExpr}
	 * labeled alternative in {@link CalcLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterStringExpr(CalcLangParser.StringExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StringExpr}
	 * labeled alternative in {@link CalcLangParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitStringExpr(CalcLangParser.StringExprContext ctx);
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