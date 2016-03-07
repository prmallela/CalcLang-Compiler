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
	 * Enter a parse tree produced by {@link ConfigParser#sec}.
	 * @param ctx the parse tree
	 */
	void enterSec(ConfigParser.SecContext ctx);
	/**
	 * Exit a parse tree produced by {@link ConfigParser#sec}.
	 * @param ctx the parse tree
	 */
	void exitSec(ConfigParser.SecContext ctx);
	/**
	 * Enter a parse tree produced by {@link ConfigParser#head}.
	 * @param ctx the parse tree
	 */
	void enterHead(ConfigParser.HeadContext ctx);
	/**
	 * Exit a parse tree produced by {@link ConfigParser#head}.
	 * @param ctx the parse tree
	 */
	void exitHead(ConfigParser.HeadContext ctx);
	/**
	 * Enter a parse tree produced by {@link ConfigParser#val}.
	 * @param ctx the parse tree
	 */
	void enterVal(ConfigParser.ValContext ctx);
	/**
	 * Exit a parse tree produced by {@link ConfigParser#val}.
	 * @param ctx the parse tree
	 */
	void exitVal(ConfigParser.ValContext ctx);
	/**
	 * Enter a parse tree produced by {@link ConfigParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(ConfigParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ConfigParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(ConfigParser.ValueContext ctx);
}