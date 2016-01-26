package edu.liu.cs664s16;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	/* write your code here*/
        System.out.println("Enter a String");
        String x,y;
        Scanner in = new Scanner(System.in);
        x = in.nextLine();
        y=CommentLexer.stripComments(x);
        System.out.println(y);
    }
}
