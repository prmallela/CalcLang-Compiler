import java.io.File;
import java.io.FilenameFilter;

/* This class provides a FilenameFilter so that we can match
 * just files ending in ".conf".
 */
public class ConfFileFilter implements FilenameFilter {
    @Override
    public boolean accept(File file, String s) {
        return s.endsWith(".conf");
    }

    public String baseName(String s) {
        return s.replace(".conf", "");
    }
}
