package duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Represents the user interface of the Duke application.
 */
public class Ui {
    private static final String DIVIDER = "-".repeat(60);
    private static final String CHATBOT_NAME = "Cicada";
    private static final String LOGO =  " _____ _               _\n"
                                +       "/  __ (_)             | |\n"
                                +       "| /  \\/_  ___ __ _  __| | __ _\n"
                                +       "| |   | |/ __/ _` |/ _` |/ _` |\n"
                                +       "| \\__/\\ | (_| (_| | (_| | (_| |\n"
                                +       " \\____/_|\\___\\__,_|\\__,_|\\__,_|\n";
    private final Scanner in;
    private final PrintStream out;

    /**
     * Constructs a new Ui object with the standard input and output streams.
     */
    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Constructs a new Ui object with the specified input and output streams.
     *
     * @param in  The input stream.
     * @param out The output stream.
     */
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Displays a line divider.
     */
    public String showLine() {
        return DIVIDER;
    }

    /**
     * Displays the welcome message.
     */
    public String showWelcome() {
        //return "Hello from"  ;
        return "Hello! I'm " + CHATBOT_NAME + "\nWhat can I do for you?";
    }

    /**
     * Reads a user command from the input stream.
     *
     * @return The user command.
     */
    public String getUserCommand() {
        out.println("Type your command and press Enter. Type 'bye' to quit.");
        return in.nextLine();
    }

    /**
     * Displays the goodbye message.
     */
    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }
}
