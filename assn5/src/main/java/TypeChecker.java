import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.io.InputStreamReader;

public class TypeChecker extends BaseErrorListener {

    public int parseErrors = 0;
    public TypeCheckingVisitor visitor;

    public TypeChecker(ANTLRInputStream input) {
        CalcLangLexer lexer = new CalcLangLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CalcLangParser parser = new CalcLangParser(tokens);
        parser.addErrorListener(this);
        ParseTree tree = parser.top();
        visitor = new TypeCheckingVisitor(tokens);
        if(parseErrors == 0) {
            // Run the type checker only if parsing succeeds.
            tree.accept(visitor);
        }
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        parseErrors++;
        super.syntaxError(recognizer, offendingSymbol, line, charPositionInLine, msg, e);
    }

    public static void main(String[] args) throws IOException {
        //ANTLRInputStream input = new ANTLRInputStream("x=6.;print 3.+(1.2\n  /\nx^\nfloor(9));");
        System.out.println("Type your program below, use ^D (cmd-D) to end.");
        ANTLRInputStream input = new ANTLRInputStream(new InputStreamReader(System.in));
        TypeChecker checker = new TypeChecker(input);
        if(checker.parseErrors > 0) {
            System.err.println("There were parse errors.");
        }
        else if(checker.visitor.errors > 0) {
            System.err.println("There were type errors.");
        }
    }

}
