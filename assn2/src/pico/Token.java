package pico;

import org.junit.Assert;

import java.math.BigInteger;
import java.util.List;

public class Token {
    //// TOKEN DATA
    Type type;          // Required: what type of token is this?
    int line, column;   // Optional: where is it in the source?
    String text;        // Variant: only used for type in (STR,SYM,OP)

    enum Type {
        EOF,    // End of input
        LBRACE, // Left curly '{'
        RBRACE, // Right curly '}'
        INT,    // Integer literal
        STR,    // String literal
        SYM,    // Unevaluated symbol
        OP,     // Named operator, such as 'add' or 'dup'
    }

    /** Basic constructor */
    Token(Type type, String text) {
        this.type = type;
        this.text = text;
    }

    /** Convenience constructor for when `text` can be null. */
    Token(Type type) {
        this(type, null);
    }

    /** Provide the position information */
    public Token setPosition(int line, int column) {
        if(line <= 0) throw new IllegalArgumentException("Line# should be >0");
        this.line = line;
        this.column = column;
        return this;
    }

    public Token clearPosition() {
        line = column = 0;
        return this;
    }

    /** We override toString so we can get sensible output when we try to
     * print tokens.
     */
    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append(type);
        switch(type) {
            case INT:
            case STR:
            case SYM:
            case OP:
                // Half-hearted attempt to escape special chars
                // (it's only for debugging output)
                String escaped = text
                        .replace("\n", "\\n")
                        .replace("(", "\\(")
                        .replace(")", "\\)");
                buf.append('(');
                buf.append(escaped);
                buf.append(')');
                break;
        }
        if(line > 0) {
            buf.append("@" + line + ":" + column);
        }
        return buf.toString();
    }

    /** Just a short program to illustrate some of the capabilities of Tokens.
     */
    public static void main(String[] args) {
        Token t1 = new Token(Type.SYM, "square");
        showEqual(t1, "SYM(square)");

        Token t2 = new Token(Type.INT, "298347509328760987");
        showEqual(t2, "INT(298347509328760987)");

        Token t3 = new Token(Type.LBRACE).setPosition(2,5);
        showEqual(t3, "LBRACE@2:5");

        Token t4 = new Token(Type.OP, "dup");
        showEqual(t4, "OP(dup)");

        Token t5 = new Token(Type.STR, "hello world!").setPosition(15,8);
        showEqual(t5, "STR(hello world!)@15:8");
    }

    static void showEqual(Token t, String expected) {
        String s = t.toString();
        System.out.println(s);
        Assert.assertEquals(expected, s);
    }
}
