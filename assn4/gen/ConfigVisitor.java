// Generated from D:/GitLab/cs664s16/assn4/src/main/antlr\Config.g4 by ANTLR 4.5.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ConfigParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ConfigVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ConfigParser#top}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTop(ConfigParser.TopContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FirstStmt}
	 * labeled alternative in {@link ConfigParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFirstStmt(ConfigParser.FirstStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConfigParser#heading}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeading(ConfigParser.HeadingContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AssignStmt}
	 * labeled alternative in {@link ConfigParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignStmt(ConfigParser.AssignStmtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NegExpr}
	 * labeled alternative in {@link ConfigParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegExpr(ConfigParser.NegExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code VarExpr}
	 * labeled alternative in {@link ConfigParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarExpr(ConfigParser.VarExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code skip}
	 * labeled alternative in {@link ConfigParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSkip(ConfigParser.SkipContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SpaceExpr}
	 * labeled alternative in {@link ConfigParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpaceExpr(ConfigParser.SpaceExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code WhiteSpace}
	 * labeled alternative in {@link ConfigParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhiteSpace(ConfigParser.WhiteSpaceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code braces}
	 * labeled alternative in {@link ConfigParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBraces(ConfigParser.BracesContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConfigParser#link}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLink(ConfigParser.LinkContext ctx);
}