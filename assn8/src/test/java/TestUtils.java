import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestUtils {

    public static void assertWellTyped(TypeChecker checker) {
        assertEquals(0, checker.getNumParseErrors());
        assertEquals(0, checker.getNumTypeErrors());
    }

    public static ArrayList<String> readLines(BufferedReader reader,
                                              ArrayList<String> lines)
            throws IOException
    {
        if(lines == null) {
            lines = new ArrayList<>();
        }
        for(String line = reader.readLine();
                line != null;
                line = reader.readLine())
        {
            lines.add(line);
        }
        return lines;
    }
}
