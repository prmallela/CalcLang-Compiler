package edu.liu.cs664s16;

public class CommentLexer {
    enum State {S, F}

    public static String stripComments(String source) {
        State state = State.S;  // Start state
        StringBuilder srcs = new StringBuilder();
        for (int position = 0; position < source.length(); position++) {
            char current = source.charAt(position);
            switch (state) {
                case S:
                    if ('(' == current && (position + 1 == source.length() ? false : '*' == source.charAt(position+1))) {
                        position++;
                        state = State.F;
                    } else {
                        srcs.append(current);
                    }
                    break;

                case F:
                    if ('*' == current && (position + 1 == source.length() ? false : ')' == source.charAt(position+1))) {
                        position++;
                        state = State.S;
                    }
                    break;
            }
        }
            if (state == State.F) {
                throw new UnterminatedCommentExn();
            }
        return srcs.toString();
    }
}