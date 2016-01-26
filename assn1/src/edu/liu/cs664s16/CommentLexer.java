package edu.liu.cs664s16;

public class CommentLexer {

    public static String stripComments(String source) {
        int j = 0, k = 0;
        String f;
        f = "";
        char[] y;
        char[] z;
        char[] x;
        y = new char[source.length()];
        z = new char[source.length()];
        x = source.toCharArray();
        boolean skip = false;
        boolean exit1=true;
        for (int i = 0; i < x.length; i++) {

            if (x[i] == '(' && x[i + 1] == '*') {
                skip = true;
                z[k] = x[i];
                k++;
                i++;
            } else if (x[i] == '*' && (x.length > i + 1 ? x[i + 1] == ')' : false) && skip) {
                skip = false;
                z = null;
                i = i + 2;
            }
            if (!skip && i < x.length) {
                y[j] = x[i];
                j++;
            } else if (skip && i < x.length) {
                z[k] = x[i];
                k++;
            }
        }
        if (skip) {
            //Append z to Y
            String zstr = new String(z);
            String yystr = new String(y);
            zstr.trim();
            yystr.trim();
            if (zstr != null) {
                f = yystr + zstr;
                exit1=false;
            }
        }
        String ystr = new String(y);
        ystr.trim();
        if (exit1) f = ystr;
        return f.trim();
    }
}
