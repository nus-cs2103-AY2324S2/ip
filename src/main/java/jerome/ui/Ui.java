package jerome.ui;

import java.util.Scanner;

import jerome.common.Messages;

/**
 * Represents the User Interface of the application.
 * @@author se-edu
 * Reuse from https://github.com/se-edu/addressbook-level2
 * with minor modifications to cater for differences in
 * program design.
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
