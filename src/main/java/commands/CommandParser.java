package commands;

import irwyn.exceptions.IrwynException;
import irwyn.exceptions.TaskException;

/**
 * This class encapsulates Command Parser which parses commands.
 *
 * @author Irwyn Liong
 * @version Week-3
 */
public class CommandParser {

    /**
     * Returns the parsed command.
     *
     * @param input Inputs from user.
     * @return Command from user input.
     * @throws IrwynException If the input is an invalid command.
     */
    public static Command parse(String input) throws IrwynException {
        String command = input.split(" ")[0];
        switch (command) {
        case "bye":
            return new EndCommand();
        case "list":
            return new ListCommand();
        case "todo":
            return new ToDoCommand(input);
        case "deadline":
            return new DeadlineCommand(input);
        case "event":
            return new EventCommand(input);
        case "mark":
            return new MarkCommand(input);
        case "unmark":
            return new UnmarkCommand(input);
        case "delete":
            return new DeleteCommand(input);
        case "find":
            return new FindCommand(input);
        case "sort":
            return new SortCommand();
        default:
            throw new TaskException();
        }
    }
}
