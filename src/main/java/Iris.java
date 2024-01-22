import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Iris {
    private BufferedReader reader;
    private final String INDENT = "    ";
    private final String CHATDELIMITER = INDENT + "_____________________";
    private List<String> cachedMessages;
    public Iris() {
        this.reader = new BufferedReader(
                new InputStreamReader(System.in));
        this.cachedMessages = new ArrayList<>();
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

    public void add() throws IOException {
        while (true) {
            String line = this.reader.readLine();
            switch (line) {
                case "bye": return;
                case "list":
                    this.print(this.listMessages());
                    break;
                default:
                    this.cachedMessages.add(line);
                    this.print("Added: " + line);
            }
        }
    }

    private String listMessages() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < this.cachedMessages.size(); i++) {
            str.append((i+1) + ". " + this.cachedMessages.get(i) + "\n");
        }
        return str.toString();
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
