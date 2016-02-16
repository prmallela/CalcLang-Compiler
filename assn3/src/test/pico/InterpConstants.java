package pico;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static pico.Assertions.assertStack;

public class InterpConstants {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(5);

    @Test
    public void pushInts() {
        assertStack(
                "3 52 -7",  // Pico program
                "3",        // Expected on bottom of stack
                "52",
                "-7"        // Expected at top of stack
        );
    }

    @Test
    public void pushSyms() {
        assertStack(
                "/foo /bar",
                "foo",
                "bar"
        );
    }

    @Test
    public void pushStrings() {
        assertStack(
                "(Hello world)(Test string)",
                "Hello world",
                "Test string"
        );
    }

    @Test
    public void pushBlock() {
        assertStack(" { dup mul } ",
                "[OP(dup)@1:4, OP(mul)@1:8]");
    }

    @Test
    public void pushNestedBlock() {
        assertStack("{dup exch {5 add}}",
                "[OP(dup)@1:2, OP(exch)@1:6," +
                        " LBRACE@1:11, INT(5)@1:12, OP(add)@1:14, RBRACE@1:17]"
        );
    }

    @Test
    public void pushNestedBlock2() {
        assertStack("{3{4 6}8}",
                "[INT(3)@1:2, LBRACE@1:3, INT(4)@1:4, INT(6)@1:6, RBRACE@1:7, INT(8)@1:8]"
                );
    }

    @Test(expected=RuntimeError.class)
    public void unmatchedLBrace() {
        assertStack("{3 9 {4}");
    }
}
