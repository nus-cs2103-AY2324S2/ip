package services;

import java.util.Scanner;

/**
 * The {@code UI} class handles the user interface for the application.
 * It provides methods to display messages to the user and to read user input.
 */
public class UI {
    private final Scanner scanner;

    /**
     * Constructs a new {@code UI} object.
     * Initializes the scanner to read input from the standard input stream.
     */
    public UI() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the next command from the user input.
     *
     * @return A string representing the user input.
     */
    public String nextCommand() {
        return this.scanner.nextLine();
    }

    /**
     * Prints the introductory message to the user.
     */
    public void printIntroMessage() {
        printHorizontalLine();
        System.out.println("\tHello! I'm RoeBot!");
        System.out.println("\tWhat can I do for you?");
        printHorizontalLine();
    }

    /**
     * Prints the exit message to the user and closes the scanner.
     */
    public void printExitMessage() {
        System.out.println("\tBye. Hope to see you again soon!");
        this.scanner.close();
    }

    /**
     * Prints a given message to the user.
     *
     * @param message The message to be printed.
     */
    public void printMessage(String message) {
        System.out.println("\t" + message);
    }

    /**
     * Prints a horizontal line for UI separation.
     */
    public void printHorizontalLine() {
        System.out.println("\t_________________________________________________");
    }
}
