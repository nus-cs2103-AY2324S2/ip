package lex.ui;

import java.util.Scanner;

/**
 * Represents the user interface.
 */
public class Ui {
    /**
     * The separator constant.
     */
    public static final String SEPARATOR = "____________________________________________________________";

    private final Scanner scanner;

    /**
     * Constructor for the Ui class.
     *
     * @param scanner The scanner.
     */
    public Ui(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Prints the welcome message.
     */
    public void welcome() {
        print(SEPARATOR);
        print("Hello! I'm lex.Lex\nWhat can I do for you?\n");
    }

    /**
     * Prints the given message.
     *
     * @param message The message.
     */
    public void print(String message) {
        System.out.println(message);
    }

    /**
     * Reads the user input.
     *
     * @return The user input.
     */
    public String read() {
        print(SEPARATOR);
        return scanner.nextLine();
    }

    /**
     * Dispose resources.
     */
    public void dispose() {
        scanner.close();
    }
}
