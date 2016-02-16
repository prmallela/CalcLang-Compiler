package pico;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

/** Tokenizer for Picoscript */
public class Lexer implements TokenSource {

    /** We'll use the character representation of -1 to represent end-of-input
     * (the -1 actually becomes 65535 due to Java's UNSIGNED two-byte
     * characters, but that code point is uninhabited in Unicode so it's safe).
     */
    static final char EOF = (char)-1;

    Reader reader;     // The input character stream
    char lookahead;    // The single lookahead character
    int line = 1, column = 0;  // Current position


    /** Basic constructor uses the `Reader` type, so we can get one char at
     * a time.
     */
    public Lexer(Reader reader) {
        this.reader = reader;
        consume();
    }

    /** Convenience constructor for tokenizing a string. */
    public Lexer(String s) {
        this(new StringReader(s));
    }

    /** Discard the current lookahead character and load the next one. */
    private void consume() {
        try {
            lookahead = (char)reader.read();
            if(lookahead == '\n') {
                line++; column=0;
            } else {
                column++;
            }
        }
        catch(IOException exn) {
            lookahead = EOF;
        }
    }

    public Token nextToken() {
        // TODO: this is the heart of your lexer
        while(lookahead != EOF) {
            Token t;
            int tokenLine = line;
            int tokenColumn = column;
            switch(lookahead) {
                case ' ':
                case '\t':
                case '\n':
                case '\r':
                    consume();
                    continue;
                case '%':
                    skipComment();
                    continue;
                case '{':
                    consume();
                    t = new Token(Token.Type.LBRACE);
                    break;
                case '}':
                    consume();
                    t = new Token(Token.Type.RBRACE);
                    break;
                case '/':
                    consume();
                    t = new Token(Token.Type.SYM, scanIdentifier());
                    break;
                case '(':
                    consume();
                    t = scanString();
                    break;
                default:
                    if(Character.isDigit(lookahead) ||
                            lookahead == '-') {
                        t = scanInteger();
                    }
                    else if(Character.isAlphabetic(lookahead)) {
                        t = new Token(Token.Type.OP, scanIdentifier());
                    }
                    else {
                        throw new LexError(line, column,
                                "Invalid character: " + lookahead);
                    }
            }
            if(t != null) return t.setPosition(tokenLine, tokenColumn);
        }
        return new Token(Token.Type.EOF);
    }

    void skipComment() {
        do {
            consume();
        } while(lookahead != '\n' && lookahead != EOF);
    }

    Token scanInteger() {
        StringBuilder buffer = new StringBuilder();
        do {
            buffer.append(lookahead);
            consume();
        } while(Character.isDigit(lookahead));
        return new Token(Token.Type.INT, buffer.toString());
    }

    static boolean isSeparator(char c) {
        switch(c) {
            case ' ':
            case '\n':
            case '\t':
            case '\r':
            case '{':
            case '}':
            case '(':
            case ')':
            case '%':
            case EOF:
                return true;
            default:
                return false;
        }
    }

    String scanIdentifier() {
        StringBuilder buffer = new StringBuilder();
        do {
            buffer.append(lookahead);
            consume();
        } while(!isSeparator(lookahead));
        return buffer.toString();
    }

    Token scanString() {
        StringBuilder buffer = new StringBuilder();
        int depth = 1;
        while(true)
        {
            switch(lookahead) {
                case '(':
                    depth++;
                    break;
                case ')':
                    depth--;
                    if(depth == 0) {
                        consume();
                        return new Token(Token.Type.STR, buffer.toString());
                    }
                    break;
                case '\\':
                    consume();
                    switch(lookahead) {
                        case 'n': lookahead = '\n'; break;
                    }
                    break;
                case EOF:
                    throw new LexError(line, column, "Unterminated string");
            }
            buffer.append(lookahead);
            consume();
        }
    }

    /** Here is a sample program that reads an expression from standard
     * input. Press control-D (or maybe control-Z) to send the EOF and
     * print the tokens.
     */
    public static void main(String[] args) {
        /* Read from standard input */
        System.out.println(
                "Patiently awaiting your code (^D or ^Z to end)");
        Lexer lexer = new Lexer(new InputStreamReader(System.in));
        Token token;
        do {
            token = lexer.nextToken();
            System.out.println(token);
        } while(token.type != Token.Type.EOF);
    }

}