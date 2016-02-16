package pico;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static pico.Assertions.assertStack;

public class InterpDict {

    @Rule
    public Timeout globalTimeout = Timeout.seconds(5);

    @Test
    public void defineInt() {
        assertStack(
                "/radius 315 def radius",
                "315"
        );
    }

    @Test
    public void defineStr() {
        assertStack(
                "/greeting(Hello world!)def greeting",
                "Hello world!"
        );
    }

    @Test
    public void defineProcedure() {
        assertStack(
                "/square{dup mul}def 9 square",
                "81"
        );
    }
}
