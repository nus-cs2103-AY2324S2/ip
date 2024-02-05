package henry;

import henry.command.ByeCommand;
import henry.command.Command;
import henry.command.DeadlineCommand;
import henry.command.DeleteCommand;
import henry.command.EventCommand;
import henry.command.FindCommand;
import henry.command.ListCommand;
import henry.command.MarkCommand;
import henry.command.TodoCommand;
import henry.command.UnknownCommand;
import henry.command.UnmarkCommand;

/**
 * Represents a Parser object for processing commands.
 */
public class Parser {
    private enum CommandType {
        LIST, FIND, UNMARK, MARK, DELETE, TODO, DEADLINE, EVENT, BYE, UNKNOWN
    }

    /**
     * Handles the command and returns the corresponding Command object.
     *
     * @param commandType The type of command.
     * @param args The arguments for the command.
     * @return The corresponding Command object.
     * @throws HenryException If the command is invalid.
     */
    private static Command handleCommand(CommandType commandType, String args) throws HenryException {
        switch (commandType) {
        case LIST:
            return new ListCommand();
        case FIND:
            return new FindCommand(args);
        case MARK:
            return new MarkCommand(args);
        case UNMARK:
            return new UnmarkCommand(args);
        case TODO:
            return new TodoCommand(args);
        case DEADLINE:
            return new DeadlineCommand(args);
        case EVENT:
            return new EventCommand(args);
        case DELETE:
            return new DeleteCommand(args);
        case BYE:
            return new ByeCommand();
        default:
            return new UnknownCommand();
        }
    }

    /**
     * Parses the full command and returns the corresponding Command object.
     *
     * @param fullCommand The full command string.
     * @return The corresponding Command object.
     * @throws HenryException If the command is invalid.
     */
    public static Command parse(String fullCommand) throws HenryException {
        String[] command = fullCommand.split(" ", 2);

        CommandType commandType;
        String params = command.length < 2 ? "" : command[1];

        try {
            commandType = CommandType.valueOf(command[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            commandType = CommandType.UNKNOWN;
        }

        return handleCommand(commandType, params);
    }
}
