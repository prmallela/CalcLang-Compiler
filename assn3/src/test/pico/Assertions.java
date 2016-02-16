package pico;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class Assertions {
    public static void assertStack(String program, String... values)
    {
        Interpreter i = new Interpreter();
        i.interpret(new Lexer(program));
        // Convert both expected and actual stack values to strings.
        ArrayList<String> expected = new ArrayList<>();
        for(String value : values) {
            expected.add(value);
        }
        ArrayList<String> actual = new ArrayList<>();
        for(Value item : i.stack) {
            actual.add(item.toString());
        }
        assertEquals(expected, actual);
    }
}
