package duke;

import java.util.Scanner;

/**
 * Ui class that displays several ui elements onto the command-line.
 *     Not in use if application is using JavaFX GUI.
 */
public class Ui {
    private Scanner sc;
    private String hLine = "________________________________________________";
    private String greetMsg = "Hello! I'm Hatsune Miku!\n"
            + " What can I do for you?";

    /**
     * Constructs a <code>Ui</code> for ui elements.
     */
    protected Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Shows a greeting message.
     */
    protected String greet() {
        return greetMsg;
    }

    /**
     * Reads command string from input.
     *
     * @return Command string.
     */
    protected String readCommand() {
        return sc.nextLine();
    }

    /**
     * Shows a message for file load error.
     */
    protected void showLoadingError() {
        System.out.println(hLine);
        System.out.println("Error in loading file from specified folder! Creating one.");
        System.out.println(hLine);
    }

    /**
     * Displays a horizontal line.
     */
    protected void showLine() {
        System.out.println(hLine);
    }

    /**
     * Echoes the inputted message.
     *
     * @param msg Message to be echoed.
     */
    protected void echo(String msg) {
        System.out.println(msg);
        System.out.println(hLine);
    }
}
