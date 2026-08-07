package duke;

import duke.command.*;

/**
 * A class that resolves the meaning of a command line.
 */
public class Parser {
    /**
     * Resolves the command from a String.
     *
     * @param message The input line from the user.
     * @return A command that corresponds to the user's input.
     */
    public static Command parse(String message) {
        String firstWord = message.split(" ")[0].toLowerCase();
        switch (firstWord) {
            case "list":
                return new ListCommand();
            case "find":
                return new FindCommand(message);
            case "mark":
                return new MarkCommand(message);
            case "unmark":
                return new UnmarkCommand(message);
            case "delete":
                return new DeleteCommand(message);
            case "todo":
                return new ToDoCommand(message);
            case "deadline":
                return new DeadlineCommand(message);
            case "event":
                return new EventCommand(message);
            case "bye":
                return new ExitCommand();
            default:
                return new WrongCommand();
        }
    }
}
