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
import henry.command.UpdateCommand;

/**
 * Represents a Parser object for processing commands.
 */
public class Parser {
    private enum CommandType {
        LIST, FIND, UNMARK, MARK, DELETE, TODO, DEADLINE, EVENT, UPDATE, BYE, UNKNOWN
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
            assert args.isBlank() : "Command does not accept arguments";
            return new ListCommand();
        case FIND:
            assert !args.isBlank() : "Command expects arguments";
            return new FindCommand(args);
        case MARK:
            assert !args.isBlank() : "Command expects arguments";
            return new MarkCommand(args);
        case UNMARK:
            assert !args.isBlank() : "Command expects arguments";
            return new UnmarkCommand(args);
        case TODO:
            assert !args.isBlank() : "Command expects arguments";
            return new TodoCommand(args);
        case DEADLINE:
            assert !args.isBlank() : "Command expects arguments";
            return new DeadlineCommand(args);
        case UPDATE:
            assert !args.isBlank() : "Command expects arguments";
            return new UpdateCommand(args);
        case EVENT:
            assert !args.isBlank() : "Command expects arguments";
            return new EventCommand(args);
        case DELETE:
            assert !args.isBlank() : "Command expects arguments";
            return new DeleteCommand(args);
        case BYE:
            assert args.isBlank() : "Command does not accept arguments";
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
