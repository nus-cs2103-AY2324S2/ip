public class Parser {
    public static Command parse(String fullCommand) throws DukeException {
        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0];

        switch (commandWord) {
        case "bye":
             return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(fullCommand);
        case "unmark":
            return new UnmarkCommand(fullCommand);
        case "deadline", "todo", "event":
            return new AddCommand(fullCommand);
        case "delete":
            return new DeleteCommand(fullCommand);
        default:
            throw new DukeException("Unrecognized command.");
        }
    }
}
