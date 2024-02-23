package kwuntalk;

import kwuntalk.command.AddDeadlineCommand;
import kwuntalk.command.AddEventCommand;
import kwuntalk.command.AddTodoCommand;
import kwuntalk.command.ByeCommand;
import kwuntalk.command.Command;
import kwuntalk.command.DeleteCommand;
import kwuntalk.command.FindCommand;
import kwuntalk.command.ListCommand;
import kwuntalk.command.MarkCommand;
import kwuntalk.command.UnmarkCommand;
import kwuntalk.exception.InvalidArgumentException;
import kwuntalk.exception.InvalidCommandException;


/**
 * Represents a parser for the user input.
 */
public class Parser {
    private enum CommandType {
        BYE, LIST, DELETE, MARK, UNMARK, TODO, EVENT, DEADLINE, FIND
    }


    /**
     * Parses user's input based on the command and returns the command.
     *
     * @param input User input from System.in.
     * @return Command in the user input.
     * @throws InvalidCommandException If the command is unknown and invalid.
     * @throws InvalidArgumentException If the argument is missing and invalid.
     */
    public static Command parse(String input) throws InvalidCommandException, InvalidArgumentException {
        assert input != null : "User's input should never be null";

        String[] parts = input.split(" ", 2);

        assert parts[0] != null : "Command should never be null";

        String command = parts[0].toUpperCase();

        try {
            Parser.CommandType cmdType = Parser.CommandType.valueOf(command);

            switch (cmdType) {
            case BYE:
                return new ByeCommand();
            case LIST:
                return new ListCommand();
            case DELETE:
                return new DeleteCommand(parts[1]);
            case MARK:
                return new MarkCommand(parts[1]);
            case UNMARK:
                return new UnmarkCommand(parts[1]);
            case TODO:
                return new AddTodoCommand(parts[1]);
            case DEADLINE:
                return new AddDeadlineCommand(parts[1]);
            case EVENT:
                return new AddEventCommand(parts[1]);
            case FIND:
                return new FindCommand(parts[1]);
            default:
                throw new InvalidCommandException(command);
            }

        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException(command);

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidArgumentException(command);
        }
    }
}
