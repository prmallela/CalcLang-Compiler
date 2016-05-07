import calc.grammar.CalcLangLexer;
import calc.grammar.CalcLangParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Front end to the Calc language type checker. The typing rules are mostly
 * implemented by {@link TypeCheckingVisitor}. But this class provides a front
 * end that sets up the lexer and parser, and listens for parse errors. Then
 * it provides some selectors like {@link #getParseTree()} and
 * {@link #getNumParseErrors()}.
 */
public class TypeChecker extends BaseErrorListener {

    private int numParseErrors = 0;
    private final TypeCheckingVisitor visitor;
    private final ParseTree tree;

    /**
     * Constructor that type-checks code from an {@link ANTLRInputStream}.
     */
    TypeChecker(ANTLRInputStream input) {
        CalcLangLexer lexer = new CalcLangLexer(input);
        lexer.addErrorListener(this);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CalcLangParser parser = new CalcLangParser(tokens);
        parser.addErrorListener(this);
        tree = parser.top();
        visitor = new TypeCheckingVisitor(tokens);
        if (getNumParseErrors() == 0) {
            // Run the type checker only if parsing succeeds.
            tree.accept(visitor);
        }
    }

    /**
     * Listens for lexical and parse errors, so it can count how many occur.
     * @see #getNumParseErrors()
     */
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                            int line, int charPositionInLine, String msg,
                            RecognitionException e) {
        numParseErrors = getNumParseErrors() + 1;
        super.syntaxError(recognizer, offendingSymbol, line, charPositionInLine, msg, e);
    }

    /**
     * Prompt for code on standard input stream, then type-check it and report
     * any errors.
     */
    static TypeChecker fromStdin() throws IOException {
        System.out.println("Type your program below, use ^D (cmd-D) to end.");
        ANTLRInputStream input = new ANTLRInputStream(new InputStreamReader(System.in));
        TypeChecker checker = new TypeChecker(input);
        if (checker.getNumParseErrors() > 0) {
            System.err.println("There were parse errors.");
        }
        else if (checker.getNumTypeErrors() > 0) {
            System.err.println("There were type errors.");
        }
        return checker;
    }

    /**
     * A main program for interactively testing the type checker.
     * @see #fromStdin()
     */
    public static void main(String[] args) throws IOException {
        fromStdin();
    }

    /**
     * @return The number of lexical and syntax errors reported.
     */
    int getNumParseErrors() {
        return numParseErrors;
    }

    /**
     * @return The number of type errors reported.
     */
    int getNumTypeErrors() {
        return visitor.getNumTypeErrors();
    }

    /**
     * @return The total number of errors reported.
     */
    public int getNumErrors() {
        return getNumParseErrors() + getNumTypeErrors();
    }

    /**
     * @return The ANTLR parse tree data structure.
     */
    ParseTree getParseTree() {
        return tree;
    }

    /**
     * The type checker records the type it determines for each expression
     * node in the parse tree. This returns the type of any node that is
     * derived from {@link calc.grammar.CalcLangParser.ExprContext}.
     * @param ctx The parse tree node.
     * @return The type of the node.
     */
    Type getNodeType(ParseTree ctx) {
        return visitor.getNodeType(ctx);
    }

    /**
     * Whenever the type checker allows an implicit type conversion of some
     * expression node in the parse tree, it records it. This method determines
     * what coercion was applied.
     * @param ctx The parse tree node.
     * @return The target type of the node, or null if no coercion was needed.
     */
    Type getCoercion(ParseTree ctx) {
        return visitor.getCoercion(ctx);
    }
}
