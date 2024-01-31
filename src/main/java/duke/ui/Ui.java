package duke.ui;

import duke.common.Messages;

import java.util.Scanner;

/**
 * Represents the User Interface of the application.
 */
public class Ui {

    private Scanner scanner;

    /**
     * Represents the User Interface of the application.
     * Takes stdin from System standard-input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays a line separator in the console.
     */
    public void showLine() {
        System.out.println("\t ____________________________________________________________");
    }

    /**
     * Displays a welcome message in the console.
     */
    public void showWelcome() {
        showLine();
        System.out.println("\t Hello! I'm JeromeGPT");
        System.out.println("\t What can I do for you?");
        showLine();
    }

    /**
     * Displays the goodbye message in the console.
     */
    public void showGoodbye() {
        showLine();
        System.out.println("\t " + Messages.MESSAGE_GOODBYE);
        showLine();
    }

    /**
     * Reads a command from the user.
     *
     * @return the command entered by the user
     */
    public String readCommand() {
        return scanner.nextLine();
    }


}
