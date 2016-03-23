import org.antlr.v4.runtime.ANTLRFileStream;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

// All the files in `test/bad/` should parse correctly, but contain type errors.
// All the files in `tests/good/` should parse correctly, with no type errors.
public class TestTypeChecker {

    static int numTests = 0;
    static int numPass = 0;

    public static void main(String[] args) throws IOException {
        testInDir("tests/bad", false);
        testInDir("tests/good", true);
        System.err.printf("\nPassed %d/%d tests.\n\n", numPass, numTests);
    }

    private static void testInDir(String dir, boolean good) throws IOException {
        String[] fileList = new File(dir).list();
        Arrays.sort(fileList);
        for(String name : fileList) {
            TypeChecker chk = new TypeChecker(new ANTLRFileStream(
                    dir + File.separator + name));
            boolean pass;
            if(good) {
                pass = chk.parseErrors == 0 && chk.visitor.errors == 0;
            }
            else {
                pass = chk.parseErrors == 0 && chk.visitor.errors > 0;
            }
            System.err.printf("**** %s **** %s/%s **** syntax=%d, type=%d\n\n",
                    pass? "PASS" : "FAIL",
                    good? "good" : "bad",
                    name,
                    chk.parseErrors,
                    chk.visitor.errors
            );
            numTests++;
            if(pass) numPass++;
        }
    }


}
