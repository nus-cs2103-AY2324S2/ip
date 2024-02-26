package util;

import java.util.Scanner;

/**
 * The Ui class handles user interaction by providing methods for printing messages and reading user input.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a Ui object with a Scanner for user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints a horizontal divider line to separate different sections of output.
     */
    public void printDivider() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a greeting message when the chatbot starts.
     */
    public void printGreetings() {
        printDivider();
        System.out.println("Hello! I'm Edgar.");
        System.out.println("What can I do for you?");
        printDivider();
    }

    /**
     * Prints a farewell message when the chatbot exits.
     */
    public String printByeMessage() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Reads the next line of user input.
     *
     * @return The user's input as a String.
     */
    public String nextCommand() {
        return this.scanner.nextLine();
    }

    /**
     * Prints a message to the console with an indentation.
     *
     * @param message The message to be printed.
     */
    public void printMessage(String message) {
        System.out.println("\t" + message);
    }
}
