package chaterpillar.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * UI class for this application. Handles all the printing of
 * things to the CLI and GUI.
 *
 * @author marclamp
 */
public class Ui {
    private static final String LOGO =
              "                           .    .\n"
            + "                            )  (\n"
            + "      _ _ _ _ _ _ _ _ _ _ _(.--.)\n"
            + "    {{ { { { { { { { { { { ( 'v')\n"
            + "     >>>>>>>>>>>>>>>>>>>>>>>`--'>";
    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader((System.in)));

    /**
     * Prints out the message given in the String argument.
     *
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
     *
     * @return basic greeting message.
     */
    public String greet() {
        String output =
                "Hello! I'm Chaterpillar\n"
                + "What can I do for you?";

        printHorizontalLine();
        echo(LOGO);
        echo(output);
        printHorizontalLine();
        return output;
    }

    /**
     * Reads the string of command in the input line.
     *
     * @return <code>String</code> of command.
     * @throws IOException if an I/O error occurs.
     */
    public String readCommand() throws IOException {
        return reader.readLine();
    }
}
