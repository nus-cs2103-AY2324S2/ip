package duke;

import duke.command.*;

/**
 * Parses user input.
 */
public class Parser {

    /**
     * Parses user input into command for execution.
     *
     * @param input full user input string
     * @return the command based on the user input
     */
    public Command parse(String input) {
        String[] words = input.split("\\s+");
        String command = words[0];
        switch (command) {
        case "find":
            return new FindCommand(input);
        case "todo":
            return new TodoCommand(input);
        case "deadline":
            return new DeadlineCommand(input);
        case "event":
            return new EventCommand(input);
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(input);
        case "unmark":
            return new UnmarkCommand(input);
        case "delete":
            return new DeleteCommand(input);
        case "bye":
            return new ExitCommand();
        default:
            return new IncorrectCommand();
        }
    }


}
