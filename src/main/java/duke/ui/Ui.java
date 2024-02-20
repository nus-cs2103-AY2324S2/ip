package duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Represents the user interface of the application.
 */
public class Ui {
    private static final String SEP = "\t__________________________________________";
    private final Scanner in;
    private final PrintStream out;
    /**
     * Constructor for Ui.
     */
    public Ui() {
        this(System.in, System.out);
    }
    /**
     * Constructor for Ui.
     *
     * @param in The input stream of the application.
     * @param out The output stream of the application.
     */
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Prints a message to the user with dividers.
     *
     * @param message The message to be printed.
     */
    public void printMessage(String... message) {
        out.println(SEP);
        for (String m : message) {
            System.out.println("\t" + m);
        }
        out.println(SEP);
    }

    /**
     * Prints a welcome message to the user.
     */
    public void printWelcomeMessage() {
        String[] s = {"Hello! I'm JOSEPH JOSHTUR!!!", "What can I do for you?"};
        printMessage(s);
    }

    /**
     * Prints a goodbye message to the user.
     */
    public void printByeMessage() {
        String[] s = {"Bye. Hope to see you again soon!"};
        printMessage(s);
    }

    /**
     * Gets the command from the user's input on the console.
     *
     * @return The command from the user.
     */
    public String getCommand() {
        String input = in.nextLine();
        while (input.isEmpty()) {
            input = in.nextLine();
        }
        return input.trim();
    }
}
