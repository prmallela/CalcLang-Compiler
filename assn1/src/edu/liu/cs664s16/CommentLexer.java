package edu.liu.cs664s16;

public class CommentLexer {
    enum State { S, F }
    public static String stripComments(String source) {
        State state = State.S;  // Start state
        char [] src;
        char [] des;
        char [] desc;
        int desp=0;
        int descp=0;
        desc = new char[source.length()];
        des = new char[source.length()];
        src = source.toCharArray();
        for(int position = 0; position < src.length; position++) {
            char current = src[position];
            switch(state) {
                case S:
                    if('(' == src[position] && '*' == src[position+1]) {
                        position++;
                        state = State.F;}
                    else{
                        des[desp++] = src[position];}
                    break;

                case F:
                    if('*' == src[position] && ')' == src[position+1]) {
                        position++;
                        state = State.S;}
                    else {
                        desc[descp++] =src[position];}
                    break;


            }
        }
        if (state == State.F)
        {throw new UnterminatedCommentExn();}
        //System.out.println(desc);
        String dess = new String(des);
        return dess.trim();
    }
}
