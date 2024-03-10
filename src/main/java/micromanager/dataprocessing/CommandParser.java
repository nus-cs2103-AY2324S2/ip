package micromanager.dataprocessing;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import micromanager.commands.AddCommand;
import micromanager.commands.Command;
import micromanager.commands.DeleteCommand;
import micromanager.commands.ExitCommand;
import micromanager.commands.FindCommand;
import micromanager.commands.ListCommand;
import micromanager.commands.MarkCommand;
import micromanager.commands.UnknownCommand;
import micromanager.commands.UpdateCommand;
import micromanager.exceptions.DukeException;
import micromanager.tasks.Task;

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
            int index = getTargetIndex(subCommands);

            return new MarkCommand(index, mainCommand);
        }
        case "todo":
            checkIsSingleCommand(isSingleCommand);

            return new AddCommand(mainCommand, subCommands);
        case "deadline":
            return parseDeadline(mainCommand, subCommands, isSingleCommand);
        case "event":
            return parseEvent(mainCommand, subCommands, isSingleCommand);
        case "delete": {
            checkIsSingleCommand(isSingleCommand);
            int index = getTargetIndex(subCommands);

            return new DeleteCommand(index);
        }
        case "find":
            checkIsSingleCommand(isSingleCommand);

            return new FindCommand(subCommands);
        case "update":
            checkIsSingleCommand(isSingleCommand);

            return parseUpdate(subCommands);
        default:
            return new UnknownCommand();
        }
    }

    private static UpdateCommand parseUpdate(String subCommands) throws DukeException {
        String commandPattern = "^(\\d+)\\s+\\[(.*?)]$";
        Pattern regex = Pattern.compile(commandPattern);
        Matcher matcher = regex.matcher(subCommands);

        if (!matcher.matches()) {
            throw new DukeException("OOPS!!! Command format is not correct.");
        }

        int index = Integer.parseInt(matcher.group(1));
        Command newTaskCommand = parse(matcher.group(2));
        if (!(newTaskCommand instanceof AddCommand)) {
            throw new DukeException("OOPS!!! You are not updating a task.");
        }
        Task newTask = ((AddCommand) newTaskCommand).createTask();

        return new UpdateCommand(index, newTask);
    }

    private static int getTargetIndex(String subCommands) throws DukeException {
        int index = 0;
        try {
            index = Integer.parseInt(subCommands) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS! Invalid task index provided.", e);
        }
        return index;
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
