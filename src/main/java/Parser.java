public class Parser {
    private enum CommandType {
        BYE, LIST, DELETE, MARK, UNMARK, TODO, DEADLINE, EVENT
    }

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
                return new DeleteCommand(commands, tasksSize);
            case MARK:
                return new MarkCommand(commands, tasksSize);
            case UNMARK:
                return new UnmarkCommand(commands, tasksSize);
            case TODO:
                return new TodoCommand(commands);
            case DEADLINE:
                return new DeadlineCommand(commands);
            case EVENT:
                return new EventCommand(commands);
            default:
                throw new InvalidCommandException();
            }
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException();
        }
    }
}
