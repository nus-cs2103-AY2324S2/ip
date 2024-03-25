package bot;

import java.util.Scanner;

/**
 * The Ui class handles all user interactions.
 */
public class Ui {
    private Scanner scanner = new Scanner(System.in);

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Bot\nWhat can I do for you? \n");
    }

    /**
     * Reads a command from the user.
     *
     * @return The command entered by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a message to the user.
     *
     * @param message The message to print.
     */
    public void printMessage(String message) {
        System.out.println(message);
    }
}