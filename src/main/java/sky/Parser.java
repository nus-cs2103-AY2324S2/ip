package sky;
import command.AddDeadlineCommand;
import command.AddEventCommand;
import command.AddTodoCommand;
import command.ArchiveCommand;
import command.Command;
import command.DeleteCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.UnMarkCommand;
import exception.IncompleteCommandException;
import exception.NoDeadlineException;
import exception.NoPeriodException;
import exception.UnknownCommandException;

/**
 * Represents a parser to parse user input into commands.
 */
public class Parser {
    /**
     * Parses the user input into a command.
     * @param command User input.
     * @return Command to be executed.
     * @throws IncompleteCommandException If the command is incomplete.
     * @throws UnknownCommandException If the command is unknown.
     */
    public static Command parseCommand(String command) throws IncompleteCommandException, UnknownCommandException {
        String[] input = command.split(" ", 2);
        String action = input[0].toUpperCase();
        String description = "";
        int index;
        switch (action) {
        case "BYE":
            return new ExitCommand();
        case "LIST":
            return new ListCommand();
        case "MARK":
            if (input.length == 1 || input[1].equals("")) {
                throw new IncompleteCommandException(input[0]);
            }
            index = Integer.parseInt(input[1]) - 1;
            return new MarkCommand(index);
        case "UNMARK":
            if (input.length == 1 || input[1].equals("")) {
                throw new IncompleteCommandException(input[0]);
            }
            index = Integer.parseInt(input[1]) - 1;
            return new UnMarkCommand(index);
        case "DELETE":
            if (input.length == 1 || input[1].equals("")) {
                throw new IncompleteCommandException(input[0]);
            }
            index = Integer.parseInt(input[1]) - 1;
            return new DeleteCommand(index);
        case "TODO":
            if (input.length == 1 || input[1].equals("")) {
                throw new IncompleteCommandException(input[0]);
            }
            description = input[1];
            return new AddTodoCommand(description);
        case "DEADLINE":
            if (input.length == 1 || input[1].equals("")) {
                throw new IncompleteCommandException(input[0]);
            }
            input = input[1].split(" /by ", 2);
            if (input.length == 1) {
                throw new NoDeadlineException();
            }
            description = input[0];
            String by = input[1];
            return new AddDeadlineCommand(description, by);
        case "EVENT":
            if (input.length == 1 || input[1].equals("")) {
                throw new IncompleteCommandException(input[0]);
            }
            input = input[1].split(" /from ", 2);
            if (input.length == 1) {
                throw new NoPeriodException();
            }
            description = input[0];
            String[] arr = input[1].split(" /to ", 2);
            if (arr.length == 1) {
                throw new NoPeriodException();
            }
            return new AddEventCommand(description, arr[0], arr[1]);
        case "FIND":
            if (input.length == 1 || input[1].equals("")) {
                throw new IncompleteCommandException(input[0]);
            }
            description = input[1];
            return new FindCommand(description);
        case "ARCHIVE":
            return new ArchiveCommand();
        default:
            throw new UnknownCommandException();
        }
    }
}
