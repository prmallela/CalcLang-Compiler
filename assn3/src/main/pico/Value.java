package pico;

import java.math.BigInteger;
import java.util.ArrayList;

public class Value {
    enum Type {
        INT,
        SYM,
        STR,
        BLOCK,
        BOOL,
    };
    Type type;
    String text;           // used for SYM, STR
    BigInteger integer;    // used for INT
    ArrayList<Token> list; // used for BLOCK
    Boolean aBoolean;

    public Value(Boolean b){
        type = Type.BOOL;
        this.aBoolean =b;
    }

    public Value(BigInteger integer) {
        type = Type.INT;
        this.integer = integer;
    }
    public Value(String str){
        type = Type.STR;
        this.text = str;
    }

    public Value(TokenSource tokens) {
        type = Type.BLOCK;
        list = new ArrayList<>();
        int depth = 1;
        while(true)
        {
            Token t = tokens.nextToken();
            if(t.type == Token.Type.RBRACE) {
                depth--;
                if(depth == 0)
                    return;
            }
            else if(t.type == Token.Type.LBRACE) {
                depth++;
            }
            else if(t.type == Token.Type.EOF && depth!=0)
                throw new RuntimeError("Un Matched Brace");
            list.add(t);
        }
    }

    public Value(Token token) {
        switch(token.type) {
            case INT:
                type = Type.INT;
                integer = new BigInteger(token.text);
                break;
            case SYM:
                type = Type.SYM;
                text = token.text;
                break;
            case STR:
                type = Type.STR;
                text = token.text;
                break;
            default:
                throw new RuntimeError("Token cannot become a value: "
                + token);
        }
    }

    public BigInteger asInteger() {
        if(type == Type.INT) {
            return integer;
        }
        else {
            throw new RuntimeError(
                    "Expected INT, found " + type
            );
        }
    }

    public TokenSource asTokenSource() {
        if(type == Type.BLOCK) {
            return new TokenListSource(list);
        }
        else {
            throw new RuntimeError(
                    "Expected BLOCK, found " + type
            );
        }
    }

    public String asSymbol() {
        if(type == Type.SYM) {
            return text;
        }
        else {
            throw new RuntimeError(
                    "Expected SYM, found " + type
            );
        }
    }

    public Boolean aBoolean(){
        if(type == Type.BOOL){
            return aBoolean;
        }
        else {
            throw new RuntimeError("Expected BOOL"+type);
        }
    }

    @Override
    public String toString()
    {
        switch(type) {
            case INT:
                return integer.toString();
            case SYM:
            case STR:
                return text;
            case BLOCK:
                return list.toString();
            case BOOL:
                return String.valueOf(aBoolean);
            default:
                throw new RuntimeError("Unhandled type: "+type);
        }
    }


    public static void main(String[] args) {

        Token t1 = new Token(Token.Type.SYM, "foo");
        Value v1 = new Value(t1);
        System.out.println(t1);
        System.out.println(v1);
    }
}
