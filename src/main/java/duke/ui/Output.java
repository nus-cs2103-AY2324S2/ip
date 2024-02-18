package duke.ui;

import duke.parser.Parser;
import duke.storage.Storage;

/**
 * Represents the output interface for the Duke application.
 * This class handles the presentation of information to the user.
 */
public class Output {
    /**
     * The ASCII art representation of the Duke logo.
     */
    public static final String LOGO = " _____   _____  _    _ \n"
                + "|  __ \\ / ____|| |  | |\n"
                + "| |__) | (___  | |__| |\n"
                + "|  _  / \\___ \\ |  __  |\n"
                + "| | \\ \\ ____) || |  | |\n"
                + "|_|  \\_|_____/ |_|  |_|\n";
    private Parser parser;
    private Storage storage;

    /**
     * Constructs an Output object with the specified Parser and Storage.
     * @param parser The Parser object used for processing user input.
     * @param storage The Storage object used for managing data storage.
     */
    public Output(Parser parser, Storage storage) {
        this.parser = parser;
        this.storage = storage;
    }

    /**
     * Creates a decorative layer around the provided string for better visual separation.
     * @param s The string to be encapsulated within the decorative layer.
     * @return A string with a decorative layer around the provided input.
     */
    public static String layer(String s) {
        String line = "_______________________________________________________";
        return line + "\n" + s + "\n" + line;
    }

    /**
     * Generates a welcome message for the user, including the Duke logo.
     * @return A welcome message string.
     */
    public String welcome() {
        return layer("Hello! I'm \n" + LOGO + "What can I do for you?");
    }

    /**
     * Generates a farewell message for the user.
     * @return A farewell message string.
     */
    public String bye() {
        return layer("Bye. Hope to see you again soon!");
    }

    /**
     * Executes the specified input command and returns the result.
     * If the input is "bye", returns the farewell message. Otherwise, processes the input with the parser.
     * @param input The user's input command.
     * @return The result of executing the input command.
     */
    public String execute(String input) {
        if (input.equalsIgnoreCase("bye")) {
            return this.bye();
        } else {
            return layer(this.parser.parse(input));
        }
    }
}

