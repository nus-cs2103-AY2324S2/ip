/*
 * Parser.java
 * This class provides utility methods to parse user input commands and create corresponding Command objects.
 * It is used for interpreting user commands in the Duke application.
 */

package duke.task;

import duke.DukeException;
import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;

/**
 * Provides utility methods to parse user input commands and create corresponding Command objects.
 */
public class Parser {
    /**
     * Parses the user's full command and returns the corresponding Command object.
     *
     * @param fullCommand The full user command to parse.
     * @return The Command object corresponding to the parsed user command.
     * @throws DukeException If the command is unrecognized or has invalid format.
     */
    public static Command parse(String fullCommand) throws DukeException {
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
            throw new DukeException("Unrecognized command.");
        }
    }
}
