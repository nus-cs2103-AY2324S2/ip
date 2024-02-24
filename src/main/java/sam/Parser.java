package sam;

import sam.command.ByeCommand;
import sam.command.Command;
import sam.command.DeadlineCommand;
import sam.command.DeleteCommand;
import sam.command.EventCommand;
import sam.command.FindCommand;
import sam.command.ListCommand;
import sam.command.MarkCommand;
import sam.command.TodoCommand;
import sam.command.UnknownCommand;
import sam.command.UnmarkCommand;

/**
 * Represents a Parser object for processing commands.
 */
public class Parser {
    private enum CommandType {
        LIST, FIND, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, BYE, UNKNOWN
    }

    /**
     * Processes a command type and task information to create and return the corresponding Command object.
     *
     * Based on the provided CommandType, creates an instance of the appropriate Command subclass
     * and initializes it with the given task information. If the command type is unrecognized,
     * creates an UnknownCommand object.
     *
     * @param commandType the type of command to process
     * @param taskInfo    additional information related to the command (e.g., task description)
     * @return the Command object corresponding to the specified command type
     * @throws SamException if an error occurs during command processing
     */
    private static Command processCommand(CommandType commandType, String taskInfo) throws SamException {
        switch (commandType) {
            case LIST:
                return new ListCommand();
            case FIND:
                return new FindCommand(taskInfo);
            case MARK:
                return new MarkCommand(taskInfo);
            case UNMARK:
                return new UnmarkCommand(taskInfo);
            case TODO:
                return new TodoCommand(taskInfo);
            case DEADLINE:
                return new DeadlineCommand(taskInfo);
            case EVENT:
                return new EventCommand(taskInfo);
            case DELETE:
                return new DeleteCommand(taskInfo);
            case BYE:
                return new ByeCommand();
            default:
                return new UnknownCommand();
        }
    }

    /**
     * Parses userInput to determine the commandType and taskInfo, then returns the corresponding Command object.
     *
     * Splits the userInput into two parts: the command type and the task information (if present).
     * Converts the command type to uppercase and attempts to match it with a value of the CommandType enum.
     * If the command type is unrecognized, sets it to UNKNOWN.
     * Delegates the creation of the Command object to the processCommand method, passing the commandType and taskInfo.
     *
     * @param userInput the input provided by the user
     * @return the Command object corresponding to the parsed user input
     * @throws SamException if an error occurs during parsing or command processing
     */
    public static Command parse(String userInput) throws SamException {
        String[] command = userInput.split(" ", 2);

        CommandType commandType;
        String taskInfo = command.length < 2 ? "" : command[1];

        try {
            commandType = CommandType.valueOf(command[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            commandType = CommandType.UNKNOWN;
        }

        return processCommand(commandType, taskInfo);
    }
}
