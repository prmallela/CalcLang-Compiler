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
    int line=1, column=0;  // Current position
    Token token;

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
           column++;
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
                case '\r':
                   consume();
                    break;
                case '\n':line++;column=0;consume();break;
                case '{':
                   // consume();
                    token = new Token(Token.Type.LBRACE,line,column);
                    consume();
                    return token;
                case '}':
                  //  consume();
                    token = new Token(Token.Type.RBRACE,line,column);
                    consume();
                    return token;
                case '/':
                    column--;
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
                   throw new LexError(line,column,"Illegal Character");
            }
        }

        return new Token(Token.Type.EOF);
    }

    Token scanString(){
        StringBuilder buf = new StringBuilder();
        int strCol= column;
        while ((lookahead != ')' )){
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

        }
        token=new Token(Token.Type.STR, buf.toString(),line,strCol);
        consume();
        return token;
    }
    Token scanSymbol(){
        StringBuilder buf = new StringBuilder();
        int SymCol =column;
        while(!(lookahead == ' '||
                lookahead == '{' ||
                lookahead =='}'  ||
                lookahead =='('  ||
                lookahead == ')' ||
                lookahead == '%' ||
                lookahead == EOF)){
            buf.append(lookahead);
            consume();
        }
        return new Token(Token.Type.SYM, buf.toString(),line,SymCol);
    }

    Token scanOperator(){
        StringBuilder buf = new StringBuilder();
        int Opcol=column;
        while(Character.isAlphabetic(lookahead)){
            buf.append(lookahead);
            consume();
        }
        return new Token(Token.Type.OP, buf.toString(),line,Opcol);
    }

    Token scanNumber(){
        StringBuilder buf = new StringBuilder();
        int numberCol=column;
        while(Character.isDigit(lookahead) ||lookahead == '-') {
            buf.append(lookahead);
            consume();
        }
        return new Token(Token.Type.INT, buf.toString(),line,numberCol);
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
       /* String x;
        Scanner in = new Scanner(System.in);
        x = in.nextLine();
        StringReader t = new StringReader(x);
        Lexer lexer = new Lexer(t); */
        Token token;
        do {
            token = lexer.nextToken();
            System.out.println(token);
        } while(token.type != Token.Type.EOF);
    }

}
