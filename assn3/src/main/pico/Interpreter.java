package pico;

import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Stack;

public class Interpreter {

    Stack<Value> stack = new Stack<>();
    HashMap<String, Value> dict = new HashMap<>();

    public void interpret(TokenSource tokens) {
        for(Token token = tokens.nextToken();
            token.type != Token.Type.EOF;
            token = tokens.nextToken())
        {
            switch(token.type) {
                case INT:
                case STR:
                case SYM:
                    stack.push(new Value(token));
                    break;
                case LBRACE:
                    stack.push(new Value(tokens));
                    break;
                case OP:
                    // First check the dictionary.
                    Value v = dict.get(token.text);
                    if(v != null) {
                        if(v.type == Value.Type.BLOCK) {
                            interpret(v.asTokenSource());
                        }
                        else {
                            stack.push(v);
                        }
                    }
                    else {
                        try {
                            // Invoke by reflection -- looks for a public void
                            // method with same name as the operator.
                            Method m = Interpreter.class.getMethod("op_"+token.text);
                            m.invoke(this);
                        } catch (NoSuchMethodException e) {
                            throw new RuntimeError("Unknown operator: " + token);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeError(e);
                        } catch (InvocationTargetException e) {
                            throw new RuntimeError(e);
                        }
                    }
                    break;
                default:
                    throw new RuntimeError("Unhandled token type: " + token.type);
            }
        }
    }
    //Stack operators
    public void op_dup() {

        stack.push(stack.peek());
    }
    public void op_pop(){
        stack.pop();
    }

    public void op_exch(){
        Stack<Value> temp=new Stack<>();
        String x = stack.pop().toString();
        String y = stack.pop().toString();
        if (isInteger(x)){
            int tempx = Integer.parseInt(x);
            int tempy = Integer.parseInt(y);
            stack.push(new Value(BigInteger.valueOf(tempx)));
            stack.push(new Value(BigInteger.valueOf(tempy)));
        }
        else {
            stack.push(new Value(x));
            stack.push(new Value(y));
        }
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        return true;
    }
    public void op_copy(){
        Stack<String> temp = new Stack<>();
        BigInteger x =stack.pop().asInteger();
        int times = x.intValue();
        int t,t1;
        t=t1=times;
        while (times > 0) {
            temp.push(new String(stack.pop().toString()));
            times--;
        }
        Stack<String> temp1 = (Stack<String>)temp.clone();
        while(t>0)
        {
            stack.push(new Value(temp.pop().toString()));
            t--;
        }
        while (t1>0){
            stack.push(new Value(temp1.pop().toString()));
             t1--;
        }
    }
    public void op_index(){
        System.out.println(stack);
        BigInteger x = stack.pop().asInteger();
        int index = x.intValue();
        Stack<?> temp = (Stack<?>)stack.clone();
        Stack<String> temp1=new Stack<>();
        int size = stack.size();
        while(size >0)
        {
            temp1.push(new String(temp.pop().toString()));
            size--;
        }
        String y=temp1.elementAt(index);
        stack.push(new Value(String.valueOf(y)));
    }

    public void op_clear(){
        stack.removeAllElements();

    }
    public void op_count(){
        int size = stack.size();
        stack.push(new Value(BigInteger.valueOf(size)));
    }
//Arithmetic operators
    public void op_add() {
        BigInteger y = stack.pop().asInteger();
        BigInteger x = stack.pop().asInteger();
        stack.push(new Value(x.add(y)));
    }

    public void op_mul() {
        BigInteger y = stack.pop().asInteger();
        BigInteger x = stack.pop().asInteger();
        stack.push(new Value(x.multiply(y)));
    }

    public void op_def() {
        Value v = stack.pop();
        String id = stack.pop().asSymbol();
        dict.put(id, v);
    }

    public void op_idiv(){
        BigInteger y = stack.pop().asInteger();
        BigInteger x = stack.pop().asInteger();
        stack.push(new Value(x.divide(y)));
    }

    public void op_mod(){
        BigInteger y = stack.pop().asInteger();
        BigInteger x = stack.pop().asInteger();
        stack.push(new Value(x.mod(y)));
        }

    public void op_sub(){
        BigInteger y = stack.pop().asInteger();
        BigInteger x = stack.pop().asInteger();
        stack.push(new Value(x.subtract(y)));
    }

    //String operators
    public void op_length(){
        String x =stack.pop().toString();
        stack.push(new Value(BigInteger.valueOf(x.length())));
            }

    public void op_get(){
        BigInteger position = stack.pop().asInteger();
        int pos = position.intValue();
        String x =stack.pop().toString();
        int ascii = (int) x.charAt(pos);
        stack.push(new Value(BigInteger.valueOf(ascii)));

    }
    public void op_strcat(){
        String x=stack.pop().toString();
        String y=stack.pop().toString();
        stack.push(new Value(y+x));

    }
    public void op_tostr(){
        String x = stack.pop().toString();
        stack.push(new Value(x));
    }
    public void op_tochar(){
        String temp = stack.pop().toString();
        if(isInteger(temp)) {
            int x1 = Integer.parseInt(temp);
            String x=Character.toString ((char) x1);
            stack.push(new Value(x));

        }
        else {
            int x1 = Integer.parseInt(temp);
            BigInteger x = BigInteger.valueOf(x1);
            stack.push(new Value(x));
        }

    }

   //Relational operators
    public void op_true(){
        stack.push(new Value(true));
    }
    public void op_false(){
        stack.push(new Value(false));
    }
   public void op_eq(){
       String x=stack.pop().toString();
       String y =stack.pop().toString();
       if(x.equals(y)) {
           stack.removeAllElements();
           stack.push(new Value("true"));
       }
       else
           stack.push(new Value("false"));
   }
    public void op_ne(){
        String x=stack.pop().toString();
        String y =stack.pop().toString();
        if(x.equals(y)) {
            stack.push(new Value("false"));
        }
        else
            stack.push(new Value("true"));

    }
    public void op_ge(){

        String x=stack.pop().toString();
        String y =stack.pop().toString();
        int i=x.compareTo(y);
        if(i<=-1 || i==0) {
            stack.push(new Value("true"));
        }
        else
            stack.push(new Value("false"));

    }
    public void op_gt(){
        String x=stack.pop().toString();
        String y =stack.pop().toString();
        int i=x.compareTo(y);
        if(i<=-1) {
            stack.push(new Value("true"));
        }
        else
            stack.push(new Value("false"));
    }
    public void op_le(){
        String x=stack.pop().toString();
        String y =stack.pop().toString();
        int i=x.compareTo(y);
        if(i<=-1) {
            stack.push(new Value("false"));
        }
        else
            stack.push(new Value("true"));

    }
    public void op_lt(){
        String x=stack.pop().toString();
        String y =stack.pop().toString();
        int i=x.compareTo(y);
        if(i<=-1 ||i==0) {
            stack.push(new Value("false"));
        }
        else
            stack.push(new Value("true"));


    }
    public void op_and(){
        Boolean x = stack.pop().aBoolean();
        Boolean y= stack.pop().aBoolean();
        if (x && y){ stack.push(new Value(true));}
        else {stack.push(new Value(false));}
    }
    public void op_or(){
        Boolean x = stack.pop().aBoolean();
        Boolean y= stack.pop().aBoolean();
        if (x || y){ stack.push(new Value(true));}
        else {stack.push(new Value(false));}
    }
    public void op_not(){
        Boolean x = stack.pop().aBoolean();
        if(!x){stack.push(new Value(true));}
        else {stack.push(new Value(false));}
    }


   //Control operators
    public void op_if (){
        String z = stack.pop().toString();
        z=z.substring(z.indexOf('(')+1,z.indexOf(')'));
        Boolean y = stack.pop().aBoolean();
        if(y){stack.push(new Value(z));}
    }
    public void op_ifelse(){
        System.out.println(stack);
        String el = stack.pop().toString();
        el=el.substring(el.indexOf('(')+1,el.indexOf(')'));
        String z = stack.pop().toString();
        z=z.substring(z.indexOf('(')+1,z.indexOf(')'));
        Boolean y = stack.pop().aBoolean();
        if(y){stack.push(new Value(z));}
        else {stack.push(new Value(el));}
     }
    public void op_repeat(){
        System.out.println(stack);
        int fi,se,mu;
        String x,op;
        String op1,op2;
        op = stack.pop().toString();
        System.out.println(op);
        op1= op.substring(op.indexOf('[')+1,op.indexOf(','));
        op1= op1.substring(op1.indexOf('(')+1,op1.indexOf(')'));
        op2= op.substring(op.indexOf(' ')+1,op.indexOf(']'));
        op2= op2.substring(op2.indexOf('(')+1,op2.indexOf(')'));
        se = stack.pop().asInteger().intValue();
        fi = stack.pop().asInteger().intValue();
        System.out.println(fi+" "+se+" "+op1+" "+op2);
    }
    public void op_for(){
        System.out.println(stack);
        String op = stack.pop().toString();
        op= op.substring(op.indexOf('(')+1, op.indexOf(')'));
        int fo = (stack.pop().asInteger()).intValue();
        int th = (stack.pop().asInteger()).intValue();
        int se = (stack.pop().asInteger()).intValue();
        int fi = (stack.pop().asInteger()).intValue();
        System.out.println(fi+" "+se+" "+th+" "+fo+" "+op);
     }
    public void op_while(){
        System.out.println(stack);
     }


    public static void main(String[] args)
    {
        System.out.println(
                "Patiently awaiting your code (^D or ^Z to end)");
        Lexer lexer = new Lexer(new InputStreamReader(System.in));
        Interpreter i = new Interpreter();
        i.interpret(lexer);
        System.out.println(i.stack);
    }
}
