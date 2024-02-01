public class Parser {
    public Parser() {

    }

    public static Command parse(String fullCommand) throws DukeException {
        String command = fullCommand.split(" ")[0];
        switch (command) {
        case "list":
            return new ListCommand();
        case "todo":
            return new AddCommand();
        case "deadline":
            return new AddCommand();
        case "event":
            return new AddCommand();
        case "mark":
            String[] splitMark = fullCommand.split(" ");
            if (splitMark.length == 1) {
                throw new DukeException("Task number of mark cannot be empty.");
            }
            return new MarkCommand(Integer.parseInt(splitMark[1]));
        case "unmark":
            String[] splitUnmark = command.split(" ");
            if (splitUnmark.length == 1) {
                throw new DukeException("Task number of unmark cannot be empty.");
            }
            return new UnmarkCommand(Integer.parseInt(splitUnmark[1]));
        case "delete":
            String[] splitDelete = command.split(" ");
            if (splitDelete.length == 1) {
                throw new DukeException("Task number of delete cannot be empty.");
            }
            return new DeleteCommand(Integer.parseInt(splitDelete[1]));
        case "bye":
            return new ExitCommand();
        default:
            return null;
        }
    }
}
