package chingu;

import chingu.command.*;
import chingu.exception.NoCommandException;

/**
 * The class Parser deals with understanding and recording the user command.
 */

public class Parser {
    /**
     * Returns Command that is sorted by the function
     *
     * @param fullCommand full command by the user in String
     * @return Command
     * @throws NoCommandException
     */
    public static Command parse(String fullCommand) throws NoCommandException {
        String [] uCmd = fullCommand.split(" ", 2);
        if (uCmd.length < 2) {
            return handleOneWordCommand(fullCommand);
        } else {
            String firstWord = uCmd[0].trim();
            String furtherDetails = uCmd[1];
            return handleLongCommand(firstWord, furtherDetails);
        }
    }

    public static Command handleOneWordCommand(String fullCommand) throws NoCommandException {
        if (fullCommand.equals("list")) {
            return new ListCommand();
        } else if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else {
            throw new NoCommandException("Please tell me what you want me to do.");
        }
    }

    public static Command handleLongCommand(String firstWord, String furtherDetails) throws NoCommandException {
        assert !firstWord.equals("todos") : "You want to add 1 task at a time - todo";
        assert !firstWord.equals("events") : "You want to add 1 task at a time - event";
        assert !firstWord.equals("deadlines") : "You want to add 1 task at a time - deadline";
        switch (firstWord) {
            case "mark":
            case "unmark":
                return new EditCommand(firstWord, furtherDetails);
            case "todo":
            case "event":
            case "deadline":
                return new AddCommand(firstWord, furtherDetails);
            case "delete":
                return new DeleteCommand(furtherDetails);
            case "find":
                return new FindCommand(furtherDetails);
            default:
                throw new NoCommandException("Please tell me what you want me to do.");
        }
    }

}



