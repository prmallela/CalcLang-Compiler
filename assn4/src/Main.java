import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

public class Main {
    public static void main(String[] args) {
        ANTLRInputStream input = new ANTLRInputStream("6*4");
        ConfigLexer lexer = new ConfigLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ConfigParser parser = new ConfigParser(tokens);
        parser.top();

    }
}