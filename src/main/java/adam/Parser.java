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
        String[] splitCommandParts;
        String[] splitDescParts;

        switch (command) {
        case "list":
        case "ls":
            return new ListCommand();
        case "todo":
        case "td":
            // Splits the command from the description
            splitCommandParts = fullCommand.split(" ", 2);
            if (splitCommandParts.length == 1) {
                throw new AdamException("Description of a todo cannot be empty.");
            } else {
                return new AddCommand("todo", splitCommandParts[1]);
            }
        case "deadline":
        case "dl":
            splitCommandParts = fullCommand.split(" ", 2);
            if (splitCommandParts.length == 1) {
                throw new AdamException("Description of a deadline cannot be empty.");
            }

            // Splits the description from the date
            splitDescParts = splitCommandParts[1].split(" /by ", 2);
            if (splitDescParts.length == 1) {
                throw new AdamException("Date/time of a deadline cannot be empty.");
            }

            return new AddCommand("deadline", splitDescParts[0], splitDescParts[1]);
        case "event":
        case "ev":
            splitCommandParts = fullCommand.split(" ", 2);
            if (splitCommandParts.length == 1) {
                throw new AdamException("Description of a event cannot be empty.");
            }

            // Splits the description from the start and end details
            splitDescParts = splitCommandParts[1].split(" /from ", 2);
            if (splitDescParts.length == 1) {
                throw new AdamException("Start date/time of a event cannot be empty.");
            }

            // Splits the start from the end
            String[] splitFrom = splitDescParts[1].split(" /to ", 2);
            if (splitFrom.length == 1) {
                throw new AdamException("End date/time of a event cannot be empty.");
            }

            return new AddCommand("event", splitDescParts[0], splitFrom[0], splitFrom[1]);
        case "mark":
        case "mk":
            splitCommandParts = fullCommand.split(" ");
            if (splitCommandParts.length == 1) {
                throw new AdamException("Task number of mark cannot be empty.");
            }

            return new MarkCommand(Integer.parseInt(splitCommandParts[1]));
        case "unmark":
        case "umk":
            splitCommandParts = fullCommand.split(" ");
            if (splitCommandParts.length == 1) {
                throw new AdamException("Task number of unmark cannot be empty.");
            }

            return new UnmarkCommand(Integer.parseInt(splitCommandParts[1]));
        case "delete":
        case "del":
            splitCommandParts = fullCommand.split(" ");
            if (splitCommandParts.length == 1) {
                throw new AdamException("Task number of delete cannot be empty.");
            }

            return new DeleteCommand(Integer.parseInt(splitCommandParts[1]));
        case "find":
        case "f":
            splitCommandParts = fullCommand.split(" ");
            if (splitCommandParts.length == 1) {
                throw new AdamException("Task number of delete cannot be empty.");
            }

            return new FindCommand(splitCommandParts[1]);
        case "bye":
            return new ExitCommand();
        default:
            throw new AdamException("I don't know what that means.");
        }
    }
}
