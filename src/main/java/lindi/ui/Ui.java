package lindi.ui;

import java.util.Scanner;

import lindi.commands.Command;

/**
 * Text based UI of the application. Deals with interactions with the user
 */
public class Ui {
    private static final String LINE_SEPARATOR = "-".repeat(50); // For visibility purposes
    private final Scanner in;
    private final String name;

    /**
     * Creates a user interface with system.in input
     */
    public Ui(String name) {
        this.in = new Scanner(System.in);
        this.name = name;
    }

    /**
     * Returns the input line read in from user
     */
    public String getUserInput() {
        return in.nextLine();
    }

    /**
     * Prints the given string, decorated with a line separator for better visibility
     */
    private void print(String s) {
        System.out.println(LINE_SEPARATOR);
        System.out.println(s);
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays to the user the status of the given command.
     */
    public void displayCommand(Command command) {
        this.print(command.status());
    }

    /**
     * Displays to the user the exception.
     */
    public void displayError(Exception e) {
        this.print("Oops ! There was an error:\n" + e.getMessage());

    }

    /**
     * Displays a greeting message.
     */
    public void greeting() {
        this.print(String.format("Hello I'm %s\nWhat can I do for you?", this.name));
    }
}
