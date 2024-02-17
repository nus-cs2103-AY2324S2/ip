package Commands;

public class CommandParser {


    public static parseCommand(String input) {
        String[] command = input.split(" ", 2);
        switch (command[0]) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "delete":
            return new DeleteCommand(command[1]);
        case "todo":
            return new TodoCommand(command[1]);
        case "deadline":
            return new DeadlineCommand(command[1]);
        case "event":
            return new EventCommand(command[1]);
        case "mark":
            return new MarkCommand(command[1]);
        case "unmark":
            return new UnmarkCommand(command[1]);
        default:
            return new InvalidCommand();
        }
    }


}
