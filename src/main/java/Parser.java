public class Parser {
    private enum CommandType {
        BYE, LIST, DELETE, MARK, UNMARK, TODO, EVENT, DEADLINE
    }
    public static Command parse(String input) throws InvalidCommandException, InvalidArgumentException {
        String[] parts = input.split(" ", 2);

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
