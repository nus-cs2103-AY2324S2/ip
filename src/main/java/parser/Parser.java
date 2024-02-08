package parser;

import exception.IncompleteCommandException;
import exception.InvalidCommandException;

public class Parser {

    public String extractCommand(String string) {
        String[] splitString;
        splitString = string.split(" ", 2);
        return splitString[0];
    }

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