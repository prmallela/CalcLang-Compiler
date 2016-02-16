package pico;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/** Just test add and multiply, as shown in video. */
public class SimpleArith {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(5);

    @Test
    public void addTwoInts() {
        Assertions.assertStack(
                "3 5 add",
                "8"
        );
    }

    @Test
    public void addSeveralInts() {
        Assertions.assertStack(
                "4 9 5 add",
                "4",
                "14"
        );
        Assertions.assertStack(
                "4 9 5 add add",
                "18"
        );
    }

    @Test
    public void multiplyTwoInts() {
        Assertions.assertStack(
                "3 5 mul",
                "15"
        );
    }
}
