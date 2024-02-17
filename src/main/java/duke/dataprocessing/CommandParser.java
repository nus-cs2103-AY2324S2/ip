package duke.dataprocessing;

import java.time.LocalDate;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.UnknownCommand;
import duke.exceptions.DukeException;

/**
 * CommandParser class parses user input to create corresponding Command objects.
 * It interprets the user's commands and arguments to determine the appropriate action to take.
 */
public class CommandParser {
    /**
     * Parses the full command entered by the user and creates the corresponding Command object.
     *
     * @param fullCommand The full command entered by the user.
     * @return The Command object representing the parsed command.
     * @throws DukeException If an error occurs during parsing or if the command is invalid.
     */
    public static Command parse(String fullCommand) throws DukeException {
        assert fullCommand != null : "command should exist";
        String mainCommand = fullCommand.split(" ")[0];
        String subCommands = fullCommand.substring(fullCommand.indexOf(' ') + 1);
        boolean isSingleCommand = fullCommand.indexOf(' ') == -1;

        switch (mainCommand) {
        case "list":
            checkIsNotSingleCommand(isSingleCommand);

            return new ListCommand();
        case "bye":
            return new ExitCommand();
        case "mark":
        case "unmark": {
            checkIsSingleCommand(isSingleCommand);
            int index = Integer.parseInt(subCommands) - 1;
            return new MarkCommand(index, mainCommand);
        }
        case "todo":
            checkIsSingleCommand(isSingleCommand);

            return new AddCommand(mainCommand, subCommands);
        case "deadline":
            return parseDeadline(mainCommand, subCommands, isSingleCommand);
        case "event":
            return parseEvent(mainCommand, subCommands, isSingleCommand);
        case "delete":
            checkIsSingleCommand(isSingleCommand);
            int index = Integer.parseInt(subCommands) - 1;

            return new DeleteCommand(index);
        case "find":
            checkIsSingleCommand(isSingleCommand);

            return new FindCommand(subCommands);
        default:
            return new UnknownCommand();
        }
    }

    private static void checkIsSingleCommand(boolean isSingleCommand) throws DukeException {
        if (isSingleCommand) {
            throw new DukeException("OOPS!!! Some arguments are missing.");
        }
    }

    private static void checkIsNotSingleCommand(boolean isSingleCommand) throws DukeException {
        if (!isSingleCommand) {
            throw new DukeException("OOPS!!! Some arguments are missing.");
        }
    }

    private static AddCommand parseDeadline(
            String mainCommand, String subCommands, boolean isSingleCommand
    ) throws DukeException {
        checkIsSingleCommand(isSingleCommand);
        String[] deadlineCommands = subCommands.split(" /by ");
        if (deadlineCommands.length != 2) {
            throw new DukeException("OOPS!!! Invalid arguments provided.");
        }

        LocalDate deadline = DateTimeParser.parse(deadlineCommands[1]);

        return new AddCommand(mainCommand, deadlineCommands[0], deadline);
    }

    private static AddCommand parseEvent(
            String mainCommand, String subCommands, boolean isSingleCommand
    ) throws DukeException {
        checkIsSingleCommand(isSingleCommand);
        String[] eventCommands = subCommands.split(" /from ");
        if (eventCommands.length != 2) {
            throw new DukeException("OOPS!!! Invalid arguments provided.");
        }
        String[] startEnd = eventCommands[1].split(" /to ");
        if (startEnd.length != 2) {
            throw new DukeException("OOPS!!! Invalid arguments provided.");
        }

        LocalDate start = DateTimeParser.parse(startEnd[0]);
        LocalDate end = DateTimeParser.parse(startEnd[1]);

        if (end.isBefore(start)) {
            throw new DukeException("OOPS!!! Your end date is before your start date.");
        }

        return new AddCommand(mainCommand, eventCommands[0], start, end);
    }
}
