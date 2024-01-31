package duke.command;

import duke.DukeException;

public class CommandParser {
    public Command parse(String input) throws DukeException {
        String[] tokens = input.split(" ", 2);
        String name = tokens[0];
        String body = tokens.length > 1 ? tokens[1] : "";
        switch (name) {
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(body);
        case "unmark":
            return new UnmarkCommand(body);
        case "bye":
            return new ExitCommand();
        case "todo":
            return new TodoCommand(body);
        case "deadline":
            return new DeadlineCommand(body);
        case "event":
            return new EventCommand(body);
        case "delete":
            return new DeleteCommand(body);
        case "find":
            return new FindCommand(body);
        default:
            throw new UnknownCommandException("Unknown command: " + name + " " + body,
                    "I'm sorry, but I don't know what that means :<");
        }
    }
}
