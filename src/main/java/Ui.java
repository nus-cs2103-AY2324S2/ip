import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ui {
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader((System.in)));

    /**
     * Prints out the message given in the String argument.
     * @param s the message to be printed
     */
    public void echo(String s) {
        System.out.println(s);
    }

    /**
     * Prints out a horizontal line, typically used to segment
     * the start and end of a message by the chatbot.
     */
    public void printHorizontalLine() {
        String line = "-".repeat(50);
        echo(line);
    }

    /**
     * Prints the greeting message by the Chaterpillar chatbot.
     * It also prints the horizontal lines as dividers before and after the message.
     */
    public void greet() {
        printHorizontalLine();
        echo("Hello! I'm Chaterpillar");
        echo("What can I do for you?");
        printHorizontalLine();
    }

    public String readCommand() throws IOException {
        return reader.readLine();
    }
}
