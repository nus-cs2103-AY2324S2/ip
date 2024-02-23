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

}
