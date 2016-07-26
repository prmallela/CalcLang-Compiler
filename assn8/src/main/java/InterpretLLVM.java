import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by league on 5/3/16.
 */
public class InterpretLLVM {

    public static final String LLI = "/usr/local/opt/llvm37/bin/lli-3.7";

    public static void runInteractively(IRGraph graph) throws IOException {
        // We'll dump the LLVM code to a temporary file,
        // and then interpret that. That frees up standard
        // input of the LLI process to be used for program
        // input.
        File llFile = File.createTempFile("calc", ".ll");
        FileOutputStream llOut = new FileOutputStream(llFile);
        try {
            new IRtoLLVM(graph, new PrintStream(llOut));
            llOut.close();
            System.out.println("Created " + llFile);
            Process lli = new ProcessBuilder(LLI, llFile.getAbsolutePath())
                    .inheritIO()
                    .start();
            lli.waitFor();
            System.out.printf("LLI exited with %d\n", lli.exitValue());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            llFile.delete();
        }
    }

    public static void runBatch(IRGraph graph, ArrayList<String> out)
            throws IOException, InterruptedException
    {
        Process lli = new ProcessBuilder(LLI)
                .redirectInput(ProcessBuilder.Redirect.PIPE)
                .redirectOutput(ProcessBuilder.Redirect.PIPE)
                .redirectError(ProcessBuilder.Redirect.INHERIT)
                .start();
        OutputStream lliStdin = lli.getOutputStream();
        new IRtoLLVM(graph, new PrintStream(lliStdin));
        lliStdin.close();
        // Read stdout into array
        BufferedReader lliStdout =
                new BufferedReader(new InputStreamReader(lli.getInputStream()));
        Utils.readLines(lliStdout, out);
        lli.waitFor(5, TimeUnit.SECONDS);
        System.out.println("========\n");
        System.out.printf("LLI exited with %d\n", lli.exitValue());
    }
}
