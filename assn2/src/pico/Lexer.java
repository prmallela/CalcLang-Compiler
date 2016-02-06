package pico;

import com.sun.xml.internal.fastinfoset.util.CharArray;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.Scanner;

/** Tokenizer for Picoscript */
public class Lexer {

    /** We'll use the character representation of -1 to represent end-of-input
     * (the -1 actually becomes 65535 due to Java's UNSIGNED two-byte
     * characters, but that code point is uninhabited in Unicode so it's safe).
     */
    static final char EOF = (char)-1;
    Reader reader;     // The input character stream
    char lookahead;    // The single lookahead character
    int line, column;  // Current position


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
        // TODO: update line and column counters
       try {
            lookahead = (char)reader.read();
        }
        catch(IOException exn) {
            lookahead = EOF;
        }
    }

    public Token nextToken() {
        // TODO: this is the heart of your lexer
        while (lookahead !=EOF){
            switch (lookahead){
                case ' ':
                case '\t':
                case '\n':
                case '\r':
                    consume();
                    break;
                case '{':
                    consume();
                    return new Token(Token.Type.LBRACE);
                case '}':
                    consume();
                    return new Token(Token.Type.RBRACE);
                case '/':
                    consume();
                    return scanSymbol();
                case '(':
                    consume();
                    return scanString();
                case '%':
                    do {
                        consume();
                    }while (!(lookahead == '\n' || lookahead == EOF));
                    break;
                default:
                    if(Character.isAlphabetic(lookahead)){
                        return scanOperator();
                    }
                    else if (Character.isDigit(lookahead) || lookahead == '-') {
                        return scanNumber();
                    }
                   throw new LexError(2,3,"hello");
            }
        }

        return new Token(Token.Type.EOF);
    }

    Token scanString(){
        StringBuilder buf = new StringBuilder();
        do{
            buf.append(lookahead);
            consume();
            if(lookahead=='('){
                    do {
                        buf.append(lookahead);
                        consume();
                    }while (lookahead !=')');
                buf.append(lookahead);
                consume();
            }

        }while (!(lookahead == ')'));
                //|| Character.isAlphabetic(lookahead) ||
                //lookahead =='\\' ||
               // lookahead == ' '));
        consume();
        return new Token(Token.Type.STR, buf.toString());
    }
    Token scanSymbol(){
        StringBuilder buf = new StringBuilder();
        do {
            buf.append(lookahead);
            consume();
        }while(!(lookahead == ' '||
                lookahead == '{' ||
                lookahead =='}'  ||
                lookahead =='('  ||
                lookahead == ')' ||
                lookahead == '%' ||
                lookahead == EOF));
        return new Token(Token.Type.SYM, buf.toString());
    }

    Token scanOperator(){
        StringBuilder buf = new StringBuilder();
        do {
            buf.append(lookahead);
            consume();
        } while(Character.isAlphabetic(lookahead));
        return new Token(Token.Type.OP, buf.toString());
    }

    Token scanNumber(){
        StringBuilder buf = new StringBuilder();
        do {
            buf.append(lookahead);
            consume();
        }while(Character.isDigit(lookahead));
        return new Token(Token.Type.INT, buf.toString());
    }
    /** Here is a sample program that reads an expression from standard
     * input. Press control-D (or maybe control-Z) to send the EOF and
     * print the tokens.
     */
    public static void main(String[] args) {
        /* Read from standard input */
        System.out.println(
                "Patiently awaiting your code (^D or ^Z to end)");
       // Lexer lexer = new Lexer(new InputStreamReader(System.in));
        String x;
        Scanner in = new Scanner(System.in);
        x = in.nextLine();
        StringReader t = new StringReader(x);
        Lexer lexer = new Lexer(t);
        Token token;
        do {
            token = lexer.nextToken();
            System.out.println(token);
        } while(token.type != Token.Type.EOF);
    }

}
