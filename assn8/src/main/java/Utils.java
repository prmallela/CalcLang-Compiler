import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Utils {

    public static ArrayList<String> readLines(
            BufferedReader reader,
            ArrayList<String> lines
    ) throws IOException
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
