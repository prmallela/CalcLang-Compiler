
import org.antlr.v4.runtime.ANTLRInputStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.ArrayList;

@RunWith(Parameterized.class)
public class TestTypeOk {
    private static FindTestPrograms finder;

    @Parameterized.Parameters(name="{0}")
    public static ArrayList<String> findTests() throws Exception {
        finder = new FindTestPrograms("examples/type-ok", ".calc");
        return finder.testNames;
    }

    @Parameterized.Parameter
    public String testName;

    @Test
    public void test() throws IOException {
        ANTLRInputStream input = new ANTLRInputStream(finder.getStream(testName));
        TypeChecker checker = new TypeChecker(input);
        TestUtils.assertWellTyped(checker);
    }
}
