package adam;

import adam.command.AddCommand;
import adam.command.Command;
import adam.command.DeleteCommand;
import adam.command.ExitCommand;
import adam.command.FindCommand;
import adam.command.ListCommand;
import adam.command.MarkCommand;
import adam.command.UnmarkCommand;

/**
 * Parses the user input and creates the commands requested by the user.
 */
public class Parser {
    /**
     * Returns an instance of a Parser object to parse the user input.
     */
    public Parser() {
    }

    /**
     * Returns the command object after parsing through the user input.
     *
     * @param fullCommand The user input.
     * @return The command object created.
     * @throws AdamException If the user input is an invalid command or format.
     */
    public static Command parse(String fullCommand) throws AdamException {
        String command = fullCommand.split(" ")[0];

        switch (command) {
        case "list":
        case "ls":
            return new ListCommand();
        case "todo":
        case "td":
            return parseTodo(fullCommand);
        case "deadline":
        case "dl":
            return parseDeadline(fullCommand);
        case "event":
        case "ev":
            return parseEvent(fullCommand);
        case "mark":
        case "mk":
            return parseMark(fullCommand);
        case "unmark":
        case "umk":
            return parseUnmark(fullCommand);
        case "delete":
        case "del":
            return parseDelete(fullCommand);
        case "find":
        case "f":
            return parseFind(fullCommand);
        case "bye":
            return new ExitCommand();
        default:
            throw new AdamException("I don't know what that means.");
        }
    }

    private static AddCommand parseTodo(String fullCommand) throws AdamException {
        // Splits the command from the description
        String[] splitCommandParts = fullCommand.split(" ", 2);
        if (splitCommandParts.length == 1) {
            throw new AdamException("Description of a todo cannot be empty.");
        } else {
            return new AddCommand("todo", splitCommandParts[1]);
        }
    }

    private static AddCommand parseDeadline(String fullCommand) throws AdamException {
        String[] splitCommandParts = fullCommand.split(" ", 2);
        if (splitCommandParts.length == 1) {
            throw new AdamException("Description of a deadline cannot be empty.");
        }

        // Splits the description from the date
        String[] splitDescParts = splitCommandParts[1].split(" /by ", 2);
        if (splitDescParts.length == 1) {
            throw new AdamException("Date/time of a deadline cannot be empty.");
        }

        return new AddCommand("deadline", splitDescParts[0], splitDescParts[1]);
    }

    private static AddCommand parseEvent(String fullCommand) throws AdamException {
        String[] splitCommandParts = fullCommand.split(" ", 2);
        if (splitCommandParts.length == 1) {
            throw new AdamException("Description of a event cannot be empty.");
        }

        // Splits the description from the start and end details
        String[] splitDescParts = splitCommandParts[1].split(" /from ", 2);
        if (splitDescParts.length == 1) {
            throw new AdamException("Start date/time of a event cannot be empty.");
        }

        // Splits the start from the end
        String[] splitFrom = splitDescParts[1].split(" /to ", 2);
        if (splitFrom.length == 1) {
            throw new AdamException("End date/time of a event cannot be empty.");
        }

        return new AddCommand("event", splitDescParts[0], splitFrom[0], splitFrom[1]);
    }

    private static MarkCommand parseMark(String fullCommand) throws AdamException {
        String[] splitCommandParts = fullCommand.split(" ");
        if (splitCommandParts.length == 1) {
            throw new AdamException("Task number of mark cannot be empty.");
        }

        return new MarkCommand(Integer.parseInt(splitCommandParts[1]));
    }

    private static UnmarkCommand parseUnmark(String fullCommand) throws AdamException {
        String[] splitCommandParts = fullCommand.split(" ");
        if (splitCommandParts.length == 1) {
            throw new AdamException("Task number of unmark cannot be empty.");
        }

        return new UnmarkCommand(Integer.parseInt(splitCommandParts[1]));
    }

    private static DeleteCommand parseDelete(String fullCommand) throws AdamException {
        String[] splitCommandParts = fullCommand.split(" ");
        if (splitCommandParts.length == 1) {
            throw new AdamException("Task number of delete cannot be empty.");
        }

        return new DeleteCommand(Integer.parseInt(splitCommandParts[1]));
    }

    private static FindCommand parseFind(String fullCommand) throws AdamException {
        String[] splitCommandParts = fullCommand.split(" ");
        if (splitCommandParts.length == 1) {
            throw new AdamException("Task number of delete cannot be empty.");
        }

        return new FindCommand(splitCommandParts[1]);
    }
}
