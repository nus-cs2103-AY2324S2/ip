package lindi.parser;

import lindi.commands.Command;
import lindi.commands.CreateTaskCommand;
import lindi.commands.DeleteCommand;
import lindi.commands.ExitCommand;
import lindi.commands.FindCommand;
import lindi.commands.InvalidCommand;
import lindi.commands.ListCommand;
import lindi.commands.MarkCommand;
import lindi.commands.UnmarkCommand;

/**
 * Deals with making sense (parsing) of the user command.
 */
public class Parser {
    /**
     * Parses the user input and returns the corresponding Command
     *
     * @param userInput the user input command string
     * @return the Command corresponding to the input string.
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
            return new InvalidCommand("Uhh, English please? Haha, just kidding...\n"
                    + "but for reals I didn't really understand that :(");
        }
    }

    /**
     * Returns the index from the args
     *
     * @param args arguments from user input
     * @return index from args
     * @throws NumberFormatException if args is not a number
     */
    private static int getIndexFromArgs(String args) throws NumberFormatException {
        return Integer.parseInt(args);
    }

    /**
     * Returns an FindCommand if args are valid, else InvalidCommand
     *
     * @param args arguments from user input
     * @return FindCommand if args are valid. <p>InvalidCommand if args is null
     */
    private static Command prepareFind(String args) {
        if (args == null) {
            return new InvalidCommand("Please give me something to search for :)");
        }
        return new FindCommand(args);
    }
    /**
     * Returns an UnmarkCommand if args are valid, else InvalidCommand
     *
     * @param args arguments from user input
     * @return UnmarkCommand if args are valid. <p>InvalidCommand if args is null or non-integer
     */
    private static Command prepareUnmark(String args) {
        if (args == null) {
            return new InvalidCommand("Uh oh ! You have to give me the index "
                    + "of the task you want to modify :)");
        }
        try {
            int listIndex = getIndexFromArgs(args);
            return new UnmarkCommand(listIndex);
        } catch (NumberFormatException e) {
            return new InvalidCommand("I'm pretty sure I gave you the indexes in base 10...\n"
                    + "there shouldn't be any characters!! :(");
        }
    }

    /**
     * Returns an MarkCommand if args are valid, else InvalidCommand
     *
     * @param args arguments from user input
     * @return MarkCommand if args are valid. <p>InvalidCommand if args is null or non-integer
     */
    private static Command prepareMark(String args) {
        if (args == null) {
            return new InvalidCommand("Uh oh ! You have to give me the index "
                    + "of the task you want to modify :)");
        }
        try {
            int listIndex = getIndexFromArgs(args);
            return new MarkCommand(listIndex);
        } catch (NumberFormatException e) {
            return new InvalidCommand("I'm pretty sure I gave you the indexes in base 10...\n"
                    + "there shouldn't be any characters!! :(");
        }
    }

    /**
     * Returns an DeleteCommand if args are valid, else InvalidCommand
     *
     * @param args arguments from user input
     * @return DeleteCommand if args are valid. <p>InvalidCommand if args is null or non-integer
     */
    private static Command prepareDelete(String args) {
        if (args == null) {
            return new InvalidCommand("Uh oh ! You have to give me the index "
                    + "of the task you want to modify :)");
        }
        try {
            int listIndex = getIndexFromArgs(args);
            return new DeleteCommand(listIndex);
        } catch (NumberFormatException e) {
            return new InvalidCommand("I'm pretty sure I gave you the indexes in base 10...\n"
                    + "there shouldn't be any characters!! :(");
        }
    }
}
