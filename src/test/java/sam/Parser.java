package sam;

import sam.command.*;

public class Parser {
    private enum CommandType {
        LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, BYE, UNKNOWN
    }

    private static Command processCommand(CommandType commandType, String taskInfo) throws SamException {
        switch (commandType) {
            case LIST:
                return new ListCommand();
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