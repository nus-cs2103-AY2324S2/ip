package lindi.parser;

import lindi.commands.*;

/**
 * Deals with making sense (parsing) of the user command.
 */
public class Parser {
    /**
     * Parses the user input and returns the corresponding lindi.commands.Command
     *
     * @param userInput the user input command string
     * @return the lindi.commands.Command corresponding to the input string.
     */
    public static Command parse(String userInput) {
        // if command called, will be index 0, rest of string (args) in index 1
        String[] userInputTokens = userInput.split(" ", 2);
        String commandWord = userInputTokens[0];
        String args = userInputTokens.length == 2 ? userInputTokens[1] : null;

        switch (commandWord) {
            case "list":
                return new ListCommand();

        case "find":
            return prepareFind(args);

            case "unmark":
                return prepareUnmark(args);

            case "mark":
                return prepareMark(args);

            case "delete":
                return prepareDelete(args);

            case "todo":
                // Fallthrough
            case "event":
                // Fallthrough
            case "deadline":
                return new CreateTaskCommand(userInput);

            case "bye":
                return new ExitCommand();

            default:
                return new InvalidCommand("Uhh, English please? Haha, just kidding...\n" +
                        "but for reals I didn't really understand that :(");
        }
    }

    private static int getIndexFromArgs(String args) throws NumberFormatException {
        return Integer.parseInt(args);
    }

    private static Command prepareFind(String args) {
        if (args == null) {
            return new InvalidCommand("Please give me something to search for :)");
        }
        return new FindCommand(args);
    }

    private static Command prepareUnmark(String args) {
        if (args == null) {
            return new InvalidCommand("Uh oh ! You have to give me the index " +
                    "of the lindi.task you want to modify :)");
        }
        try {
            int listIndex = getIndexFromArgs(args);
            return new UnmarkCommand(listIndex);
        } catch (NumberFormatException e) {
            return new InvalidCommand("I'm pretty sure I gave you the indexes in base 10...\n" +
                    "there shouldn't be any characters!! :(");
        }
    }

    private static Command prepareMark(String args) {
        if (args == null) {
            return new InvalidCommand("Uh oh ! You have to give me the index " +
                    "of the lindi.task you want to modify :)");
        }
        try {
            int listIndex = getIndexFromArgs(args);
            return new MarkCommand(listIndex);
        } catch (NumberFormatException e) {
            return new InvalidCommand("I'm pretty sure I gave you the indexes in base 10...\n" +
                    "there shouldn't be any characters!! :(");
        }
    }

    private static Command prepareDelete(String args) {
        if (args == null) {
            return new InvalidCommand("Uh oh ! You have to give me the index " +
                    "of the lindi.task you want to modify :)");
        }
        try {
            int listIndex = getIndexFromArgs(args);
            return new DeleteCommand(listIndex);
        } catch (NumberFormatException e) {
            return new InvalidCommand("I'm pretty sure I gave you the indexes in base 10...\n" +
                    "there shouldn't be any characters!! :(");
        }
    }
}