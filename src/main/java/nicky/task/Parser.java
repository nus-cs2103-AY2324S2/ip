package nicky.task;

import nicky.NickyException;
import nicky.command.AddCommand;
import nicky.command.Command;
import nicky.command.DeleteCommand;
import nicky.command.ExitCommand;
import nicky.command.FindCommand;
import nicky.command.ListCommand;
import nicky.command.MarkCommand;
import nicky.command.UnmarkCommand;

/**
 * Provides utility methods to parse user input commands and create corresponding Command objects.
 * It is used for interpreting user commands in the Nicky application.
 */
public class Parser {
    /**
     * Parses the user's full command and returns the corresponding Command object.
     *
     * @param fullCommand The full user command to parse.
     * @return The Command object corresponding to the parsed user command.
     * @throws NickyException If the command is unrecognized or has invalid format.
     */
    public static Command parse(String fullCommand) throws NickyException {
        assert fullCommand != null && !fullCommand.trim().isEmpty() : "Full command cannot be null or empty";
        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0];

        switch (commandWord) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(fullCommand);
        case "unmark":
            return new UnmarkCommand(fullCommand);
        case "deadline":
        case "todo":
        case "event":
            return new AddCommand(fullCommand);
        case "delete":
            return new DeleteCommand(fullCommand);
        case "find":
            return new FindCommand(fullCommand);
        default:
            throw new NickyException("Unrecognized command.");
        }
    }
}
