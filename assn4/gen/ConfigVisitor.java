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
	 * Visit a parse tree produced by {@link ConfigParser#ignore}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIgnore(ConfigParser.IgnoreContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConfigParser#sec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSec(ConfigParser.SecContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConfigParser#head}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHead(ConfigParser.HeadContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConfigParser#val}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVal(ConfigParser.ValContext ctx);
	/**
	 * Visit a parse tree produced by {@link ConfigParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(ConfigParser.ValueContext ctx);
}