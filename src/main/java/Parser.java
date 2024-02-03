public class Parser {
    private enum CommandType {
        BYE, LIST, DELETE, MARK, UNMARK, TODO, DEADLINE, EVENT
    }

    public static Command parse(String fullCommand) throws Exception {
        String[] commands = fullCommand.split(" ", 2);
        validateCommand(commands);
        CommandType commandType = CommandType.valueOf(commands[0].toUpperCase());
        switch (commandType) {
        case BYE:
            return new ExitCommand();
        case LIST:
            return new ListCommand();
        case DELETE:
            return new DeleteCommand(Integer.parseInt(commands[1]) - 1);
        case MARK:
            return new MarkCommand(Integer.parseInt(commands[1]) - 1);
        case UNMARK:
            return new UnmarkCommand(Integer.parseInt(commands[1]) - 1);
        case TODO:
            return new TodoCommand(commands[1]);
        case DEADLINE:
            return new DeadlineCommand(commands);
        case EVENT:
            return new EventCommand(commands);
        default:
            throw new EggyException("");
        }
    }

    private static void validateCommand(String[] commands) throws Exception {
        try {
            CommandType commandType = CommandType.valueOf(commands[0].toUpperCase());
            if (commands.length < 2 && (commandType == CommandType.TODO || commandType == CommandType.DEADLINE
                    || commandType == CommandType.EVENT)) {
                throw new IncompleteTaskException(commandType.name().toLowerCase());
            } else if (commandType == CommandType.DELETE || commandType == CommandType.MARK
                    || commandType == CommandType.UNMARK) {
                if (commands.length < 2) {
                    throw new IncompleteCommandException(commandType.name().toLowerCase());
                } else {
                    try {
                        int taskNumber = Integer.parseInt(commands[1]);
                        if (taskNumber < 1 || taskNumber > tasks.size()) {
                            throw new TaskListIndexOutOfBoundsException(taskNumber, tasks.size());
                        }
                    } catch (NumberFormatException e) {
                        throw new TaskNumberFormatException();
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException();
        }
    }
}
