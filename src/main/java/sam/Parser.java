package sam;

import sam.command.Command;
import sam.command.ByeCommand;
import sam.command.ListCommand;
import sam.command.TodoCommand;
import sam.command.DeadlineCommand;
import sam.command.EventCommand;
import sam.command.MarkCommand;
import sam.command.UnmarkCommand;
import sam.command.UnknownCommand;
import sam.command.DeleteCommand;
import sam.command.FindCommand;

public class Parser {
    private enum CommandType {
        LIST, FIND, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, BYE, UNKNOWN
    }

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