package duke;

import java.util.Scanner;

/**
 * Ui deals with interactions with the user.
 */
public class Ui {

    public static final String SEPERATOR = "------------------------------------------------";
    public static final String INDENT = "    ";
    public static final String INDENT_SEPERATOR = INDENT + SEPERATOR;

    private Scanner scanner;

    private String input;

    /**
     * The constructor of Ui.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Display the welcome message.
     */
    public void showWelcome() {
        String logo = " ____\n"
                + "|  _ \\   ___   ___\n"
                + "| |_| | / _ \\ / _ \\\n"
                + "| |_| | | __/ | __/\n"
                + "|____/  \\___| \\___|\n";

        String msg = SEPERATOR
                + "\nHello! I'm Bee!\n"
                + "What can I do for you?\n"
                + SEPERATOR;

        System.out.println(logo + "\n" + msg);
    }

    /**
     * Display the goodbye message
     */
    public static void showGoodbyeMessage() {
        System.out.println(INDENT_SEPERATOR
                + "\n"
                + INDENT
                + "Bye. Hope to see you again soon!\n"
                + INDENT_SEPERATOR);
    }

    /**
     * A getter function to get the user's input.
     *
     * @return The user's input.
     */
    public String getInput() {
        return this.input;
    }

    /**
     * Read the user's input.
     *
     * @return The user's input.
     */
    public String readCommand() {
        this.input = this.scanner.nextLine();
        return this.input;
    }

}
