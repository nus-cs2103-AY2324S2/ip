package duke;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Ui class handles the user interface of the application.
 */
public class Ui {
    protected Scanner in;
    public Ui() {
        this(System.in);
    }

    public Ui(InputStream in) {
        this.in = new Scanner(in);
    }

    /**
     * Checks if the user input is empty.
     * @param rawInputLine
     * @return True if the input is empty, false otherwise.
     */
    public boolean shouldIgnore(String rawInputLine) {
        return rawInputLine.trim().isEmpty();
    }

    /**
     * Prints the welcome message when the application starts.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Taro\n" + "What can I do for you?\n");
    }

    /**
     * Prints the goodbye message when the application exits.
     */
    public void showGoodbyeMessage() {
        System.out.println("Bye bye!");
    }

    /**
     * Reads the input from the user.
     * @return The input from the user.
     */
    public String readInput() {
        String input = in.nextLine();
        while (shouldIgnore(input)) {
            input = in.nextLine();
        }
        return input;
    }

    /**
     * Exits the application.
     */
    public void exit() {
        System.out.println("Bye bye!");
        System.exit(0);
    }
}
