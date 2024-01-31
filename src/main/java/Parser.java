public class Parser {
    private enum CommandType {
        LIST, UNMARK, MARK, DELETE, TODO, DEADLINE, EVENT, BYE, UNKNOWN
    }

    private static Command handleCommand(CommandType commandType, String args) throws HenryException {
        switch (commandType) {
        case LIST:
            return new ListCommand();
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
