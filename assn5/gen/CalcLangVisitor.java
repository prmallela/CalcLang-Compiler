// Generated from D:/GitLab/cs664s16/assn5/src/main/antlr\CalcLang.g4 by ANTLR 4.5.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CalcLangParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CalcLangVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CalcLangParser#top}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTop(CalcLangParser.TopContext ctx);
	/**
	 * Visit a parse tree produced by {@link CalcLangParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(CalcLangParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AssignStmt}
	 * labeled alternative in {@link CalcLangParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignStmt(CalcLangParser.AssignStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrintStmt}
	 * labeled alternative in {@link CalcLangParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintStmt(CalcLangParser.PrintStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BoolExpr}
	 * labeled alternative in {@link CalcLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolExpr(CalcLangParser.BoolExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IfExpr}
	 * labeled alternative in {@link CalcLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfExpr(CalcLangParser.IfExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FloatExpr}
	 * labeled alternative in {@link CalcLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatExpr(CalcLangParser.FloatExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NegExpr}
	 * labeled alternative in {@link CalcLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegExpr(CalcLangParser.NegExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code VarExpr}
	 * labeled alternative in {@link CalcLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarExpr(CalcLangParser.VarExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IntExpr}
	 * labeled alternative in {@link CalcLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntExpr(CalcLangParser.IntExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParenExpr}
	 * labeled alternative in {@link CalcLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExpr(CalcLangParser.ParenExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code OpExpr}
	 * labeled alternative in {@link CalcLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpExpr(CalcLangParser.OpExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FunExpr}
	 * labeled alternative in {@link CalcLangParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunExpr(CalcLangParser.FunExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link CalcLangParser#bool}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBool(CalcLangParser.BoolContext ctx);
}