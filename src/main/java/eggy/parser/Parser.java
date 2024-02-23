package eggy.parser;

import eggy.command.Command;
import eggy.command.DeadlineCommand;
import eggy.command.DeleteCommand;
import eggy.command.EventCommand;
import eggy.command.ExitCommand;
import eggy.command.FindCommand;
import eggy.command.ListCommand;
import eggy.command.MarkCommand;
import eggy.command.TodoCommand;
import eggy.command.UnmarkCommand;
import eggy.exception.EggyException;
import eggy.exception.InvalidCommandException;

/**
 * Represents a parser that parses the user input.
 */
public class Parser {
    /** The type of command. */
    private enum CommandType {
        BYE, LIST, DELETE, MARK, UNMARK, TODO, DEADLINE, EVENT, FIND
    }

    /**
     * Parses the user input and returns the command.
     *
     * @param fullCommand The full user input.
     * @param tasksSize The size of the tasks.
     * @return The command.
     * @throws EggyException If the command is invalid.
     */
    public static Command parse(String fullCommand, int tasksSize) throws EggyException {
        String[] commands = fullCommand.split(" ", 2);
        try {
            CommandType commandType = CommandType.valueOf(commands[0].toUpperCase());
            switch (commandType) {
            case BYE:
                return new ExitCommand();
            case LIST:
                return new ListCommand();
            case DELETE:
                return new DeleteCommand(tasksSize, commands);
            case MARK:
                return new MarkCommand(tasksSize, commands);
            case UNMARK:
                return new UnmarkCommand(tasksSize, commands);
            case TODO:
                return new TodoCommand(commands);
            case DEADLINE:
                return new DeadlineCommand(commands);
            case EVENT:
                return new EventCommand(commands);
            case FIND:
                return new FindCommand(commands);
            default:
                throw new InvalidCommandException();
            }
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException();
        }
    }
}
