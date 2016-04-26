import org.antlr.v4.runtime.ANTLRInputStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class TestTypeErr {

    private static FindTestPrograms finder;

    @Parameterized.Parameters(name="{0}")
    public static ArrayList<String> findTests() throws Exception {
        finder = new FindTestPrograms("examples/type-err", ".calc");
        return finder.testNames;
    }

    @Parameterized.Parameter
    public String testName;

    @Test
    public void test() throws IOException {
        ANTLRInputStream input = new ANTLRInputStream(finder.getStream(testName));
        TypeChecker checker = new TypeChecker(input);
        assertEquals(0, checker.getNumParseErrors());
        assertTrue(checker.getNumTypeErrors() > 0);
    }
}
