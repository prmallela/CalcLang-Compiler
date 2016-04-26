import org.antlr.v4.runtime.ANTLRInputStream;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(Parameterized.class)
public class TestRunExpect {

    private static FindTestPrograms finder;
    private static final Pattern expectRegex =
            Pattern.compile(".*#expect\\s+(.*)");

    @Parameterized.Parameters(name="{0}")
    public static ArrayList<String> findTestCases()
            throws IOException, URISyntaxException
    {
        finder = new FindTestPrograms("examples/run-expect", ".calc");
        return finder.testNames;
    }

    /* The raw filename of the test, such as "00-int-const.calc".
       Populated by `findTestCases()`. */
    @Parameterized.Parameter(0)
    public String testName;

    /* Stores the `testName` prepended with the `testsDirSep`.
       Initialized first-thing in the `test()` method. */
    private String testPath;

    @Test
    public void test() throws IOException, InterruptedException {
        testPath = finder.testsDirSep + testName;
        ArrayList<String> expectedOuts = getExpectedOutputs();
        ArrayList<String> interpOuts = new ArrayList<>();

        TypeChecker checker = compile();
        ConvertToIR ir = new ConvertToIR(checker);
        System.out.println(ir.block);
        InterpretIR interp = new InterpretIR();
        interp.run(ir.block, interpOuts);
        System.out.println(interpOuts);
        Assert.assertEquals(expectedOuts, interpOuts);
    }

    private ArrayList<String> getExpectedOutputs() throws IOException {
        BufferedReader testReader = new BufferedReader(new InputStreamReader(
                getClass().getResourceAsStream(testPath)
        ));
        ArrayList<String> outs = TestUtils.readLines(testReader, null);
        ListIterator<String> it = outs.listIterator();
        while(it.hasNext()) {
            Matcher m = expectRegex.matcher(it.next());
            it.remove();
            if(m.matches()) {
                it.add(m.group(1).replace("~", ""));
            }
        }
        return outs;
    }

    private TypeChecker compile() throws IOException {
        ANTLRInputStream input = new ANTLRInputStream(
                getClass().getResourceAsStream(testPath)
        );
        // Lex, parse, and type check.
        TypeChecker checker = new TypeChecker(input);
        TestUtils.assertWellTyped(checker);
        return checker;
    }
}
