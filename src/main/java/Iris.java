import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Iris {
    private BufferedReader reader;
    private final String INDENT = "    ";
    private final String CHATDELIMITER = INDENT + "_____________________";
    public Iris() {
        this.reader = new BufferedReader(
                new InputStreamReader(System.in));
    }

    public void start(){
        this.print("Hello I am Iris.\nWhat can I do for you?");
    }

    public void echo() throws IOException {
        while (true) {
            String line = this.reader.readLine();
            if (line.equals("bye")) {
                return;
            }
            this.print(line);
        }
    }

    public void exit() {
        this.print("GoodBye. Hope to see you again.");
    }

    private void print(String output) {
        System.out.println(this.CHATDELIMITER);
        System.out.println(this.indent(output));
        System.out.println(this.CHATDELIMITER);
    }

    private String indent(String str) {
        String[] lines = str.split("\n");
        StringBuilder indentedString = new StringBuilder();
        for (String line : lines) {
            String indentedLine = this.INDENT + line;
            indentedString.append(indentedLine).append("\n");
        }
        return indentedString.toString();
    }
}
