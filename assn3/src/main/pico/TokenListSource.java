package pico;

import java.util.ArrayList;

public class TokenListSource implements TokenSource {
    ArrayList<Token> list;
    int nextIndex = 0;

    public TokenListSource(ArrayList<Token> list) {
        this.list = list;
    }

    @Override
    public Token nextToken() {
        if(nextIndex < list.size()) {
            return list.get(nextIndex++);
        }
        else {
            return new Token(Token.Type.EOF);
        }
    }
}
