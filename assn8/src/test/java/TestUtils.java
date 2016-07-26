import static org.junit.Assert.assertEquals;

public class TestUtils {

    public static void assertWellTyped(TypeChecker checker) {
        assertEquals(0, checker.getNumParseErrors());
        assertEquals(0, checker.getNumTypeErrors());
    }
}
