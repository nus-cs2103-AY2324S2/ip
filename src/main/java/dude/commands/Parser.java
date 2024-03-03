package dude.commands;

import dude.tasks.TaskList;

/**
 * The Parser class is responsible for parsing the user input and returning the appropriate command.
 */
public class Parser {

    /**
     * Parses the user input and returns the appropriate command.
     *
     * @param input    The user input.
     * @param tasklist The TaskList to be used by the command.
     * @return The parsed command from the user input.
     */
    public static Command parse(String input, TaskList tasklist) {
        assert(input != null);
        assert(tasklist != null);

        String[] command = input.split(" ", 2);
        switch (command[0]) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand(tasklist);
        case "delete":
            return new DeleteCommand(input, tasklist);
        case "todo":
            return new TodoCommand(input, tasklist);
        case "deadline":
            return new DeadlineCommand(input, tasklist);
        case "event":
            return new EventCommand(input, tasklist);
        case "mark":
            return new MarkCommand(input, tasklist);
        case "unmark":
            return new UnmarkCommand(input, tasklist);
        case "find":
            return new FindCommand(input, tasklist);
        case "help":
            return new HelpCommand(input);
        default:
            return new InvalidCommand();
        }
    }


}
