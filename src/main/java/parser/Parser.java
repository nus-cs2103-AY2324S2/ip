package parser;

import exception.IncompleteCommandException;
import exception.InvalidCommandException;

/**
 * The Parser class provides methods for extracting command and description from a string.
 */
public class Parser {

    /**
     * Extracts the command from the given string.
     *
     * @param string The input string containing the command.
     * @return The extracted command.
     */
    public String extractCommand(String string) {
        String[] splitString;
        splitString = string.split(" ", 2);
        return splitString[0];
    }

    /**
     * Extracts the description from the given string.
     *
     * @param string The input string containing the command and description.
     * @return The extracted description.
     * @throws IncompleteCommandException If the command is missing a description.
     */
    public String extractDescription(String string) throws IncompleteCommandException {
        String[] splitString;
        try {
            splitString = string.split(" ", 2);
            return splitString[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IncompleteCommandException("Command is missing a description.");
        }

    }

}