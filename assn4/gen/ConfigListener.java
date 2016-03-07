// Generated from D:/GitLab/cs664s16/assn4/src/main/antlr\Config.g4 by ANTLR 4.5.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ConfigParser}.
 */
public interface ConfigListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ConfigParser#top}.
	 * @param ctx the parse tree
	 */
	void enterTop(ConfigParser.TopContext ctx);
	/**
	 * Exit a parse tree produced by {@link ConfigParser#top}.
	 * @param ctx the parse tree
	 */
	void exitTop(ConfigParser.TopContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FirstStmt}
	 * labeled alternative in {@link ConfigParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterFirstStmt(ConfigParser.FirstStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FirstStmt}
	 * labeled alternative in {@link ConfigParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitFirstStmt(ConfigParser.FirstStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link ConfigParser#heading}.
	 * @param ctx the parse tree
	 */
	void enterHeading(ConfigParser.HeadingContext ctx);
	/**
	 * Exit a parse tree produced by {@link ConfigParser#heading}.
	 * @param ctx the parse tree
	 */
	void exitHeading(ConfigParser.HeadingContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignStmt}
	 * labeled alternative in {@link ConfigParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAssignStmt(ConfigParser.AssignStmtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignStmt}
	 * labeled alternative in {@link ConfigParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAssignStmt(ConfigParser.AssignStmtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NegExpr}
	 * labeled alternative in {@link ConfigParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNegExpr(ConfigParser.NegExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NegExpr}
	 * labeled alternative in {@link ConfigParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNegExpr(ConfigParser.NegExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VarExpr}
	 * labeled alternative in {@link ConfigParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterVarExpr(ConfigParser.VarExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VarExpr}
	 * labeled alternative in {@link ConfigParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitVarExpr(ConfigParser.VarExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code skip}
	 * labeled alternative in {@link ConfigParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterSkip(ConfigParser.SkipContext ctx);
	/**
	 * Exit a parse tree produced by the {@code skip}
	 * labeled alternative in {@link ConfigParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitSkip(ConfigParser.SkipContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SpaceExpr}
	 * labeled alternative in {@link ConfigParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterSpaceExpr(ConfigParser.SpaceExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SpaceExpr}
	 * labeled alternative in {@link ConfigParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitSpaceExpr(ConfigParser.SpaceExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WhiteSpace}
	 * labeled alternative in {@link ConfigParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterWhiteSpace(ConfigParser.WhiteSpaceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WhiteSpace}
	 * labeled alternative in {@link ConfigParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitWhiteSpace(ConfigParser.WhiteSpaceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code braces}
	 * labeled alternative in {@link ConfigParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBraces(ConfigParser.BracesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code braces}
	 * labeled alternative in {@link ConfigParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBraces(ConfigParser.BracesContext ctx);
	/**
	 * Enter a parse tree produced by {@link ConfigParser#link}.
	 * @param ctx the parse tree
	 */
	void enterLink(ConfigParser.LinkContext ctx);
	/**
	 * Exit a parse tree produced by {@link ConfigParser#link}.
	 * @param ctx the parse tree
	 */
	void exitLink(ConfigParser.LinkContext ctx);
}