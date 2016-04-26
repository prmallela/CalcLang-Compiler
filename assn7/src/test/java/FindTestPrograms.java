import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class FindTestPrograms {

    File codeLocation;
    String testsDir;
    String testsDirSep;
    String testsExtension;
    ArrayList<String> testNames = new ArrayList<>();

    public FindTestPrograms(String testsDir, String testsExtension)
            throws IOException, URISyntaxException
    {
        this.testsDir = testsDir;
        this.testsExtension = testsExtension;
        this.testsDirSep = testsDir + File.separator;
        // http://stackoverflow.com/q/11012819/
        codeLocation = new File(FindTestPrograms.class.getProtectionDomain()
                .getCodeSource().getLocation().getPath());
        // System.out.println("codeLocation: " + codeLocation);
        if(codeLocation.isFile())
        {
            findTestCasesInJar();
        }
        else
        {
            findTestCasesInDir();
        }
    }

    private void findTestCasesInDir() throws IOException, URISyntaxException {
        // We're running on the filesystem, so getResources will find
        // dirs with specific name.
        Enumeration<URL> testUrls = getClass().getClassLoader()
                .getResources(testsDir);
        while(testUrls.hasMoreElements()) {
            URL url = testUrls.nextElement();
            // It should be a `file://` URL that refers to an absolute
            // filesystem path.
            assert url.getProtocol().equals("file");
            File dir = new File(url.toURI());
            for(String name : dir.list()) {
                if(name.endsWith(testsExtension))
                {
                    // Here, the name does not include the testsDir prefix
                    testNames.add(name);
                }
            }
        }
    }

    private void findTestCasesInJar() throws IOException {
        JarFile jar = new JarFile(codeLocation);
        Enumeration<JarEntry> entries = jar.entries();
        // This will go through ALL entries in the jar file
        while(entries.hasMoreElements()) {
            String name = entries.nextElement().getName();
            // So we require
            if(name.startsWith(testsDirSep)
                && name.endsWith(testsExtension))
            {
                // Add just the name, stripping the testsDir prefix
                testNames.add(name.substring(testsDirSep.length()));
            }
        }
    }

    InputStream getStream(String testName) {
        return getClass().getResourceAsStream(testsDirSep + testName);
    }
}
