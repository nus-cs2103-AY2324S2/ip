package duke.parser;

/**
 * Provides utility methods for parsing user input into commands and arguments for the Duke application.
 */
public class Parser {
    public static String[] parse(String userInput) {
        return userInput.trim().split(" ", 2);
    }
}


