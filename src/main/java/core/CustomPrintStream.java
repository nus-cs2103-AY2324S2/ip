package core;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * A custom PrintStream class that captures printed output and provides a method to retrieve it.
 * This class extends the PrintStream class and overrides the println method to capture the output.
 */
public class CustomPrintStream extends PrintStream {
    private StringBuilder stringBuilder = new StringBuilder();

    public CustomPrintStream(OutputStream out) {
        super(out);
    }

    @Override
    public void println(String x) {
        stringBuilder.append(x).append("\n");
    }

    public String getCapturedOutput() {
        String output = stringBuilder.toString();
        stringBuilder.setLength(0);
        return output;
    }

    public void clear() {
        stringBuilder = new StringBuilder();
    }
}
