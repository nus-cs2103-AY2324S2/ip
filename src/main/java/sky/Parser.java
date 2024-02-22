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
        switch (action) {
        case "BYE":
            return new ExitCommand();
        case "LIST":
            return new ListCommand();
        case "MARK":
            return parseMarkCommand(input);
        case "UNMARK":
            return parseUnMarkCommand(input);
        case "DELETE":
            return parseDeleteCommand(input);
        case "TODO":
            return parseTodoCommand(input);
        case "DEADLINE":
            return parseDeadlineCommand(input);
        case "EVENT":
            return parseEventCommand(input);
        case "FIND":
            return parseFindCommand(input);
        case "ARCHIVE":
            return new ArchiveCommand();
        default:
            throw new UnknownCommandException();
        }
    }

    /**
     * Parses the user input into a mark command.
     * @param input User input.
     * @return Mark command to be executed.
     * @throws IncompleteCommandException If the command is incomplete.
     */
    private static Command parseMarkCommand(String[] input) throws IncompleteCommandException {
        if (input.length == 1 || input[1].equals("")) {
            throw new IncompleteCommandException(input[0]);
        }
        int index = Integer.parseInt(input[1]) - 1;
        return new MarkCommand(index);
    }

    /**
     * Parses the user input into an unmark command.
     * @param input User input.
     * @return Unmark command to be executed.
     * @throws IncompleteCommandException If the command is incomplete.
     */
    private static Command parseUnMarkCommand(String[] input) throws IncompleteCommandException {
        if (input.length == 1 || input[1].equals("")) {
            throw new IncompleteCommandException(input[0]);
        }
        int index = Integer.parseInt(input[1]) - 1;
        return new UnMarkCommand(index);
    }

    /**
     * Parses the user input into a delete command.
     * @param input User input.
     * @return Delete command to be executed.
     * @throws IncompleteCommandException If the command is incomplete.
     */
    private static Command parseDeleteCommand(String[] input) throws IncompleteCommandException {
        if (input.length == 1 || input[1].equals("")) {
            throw new IncompleteCommandException(input[0]);
        }
        int index = Integer.parseInt(input[1]) - 1;
        return new DeleteCommand(index);
    }

    /**
     * Parses the user input into a todo command.
     * @param input User input.
     * @return Todo command to be executed.
     * @throws IncompleteCommandException If the command is incomplete.
     */
    private static Command parseTodoCommand(String[] input) throws IncompleteCommandException {
        if (input.length == 1 || input[1].equals("")) {
            throw new IncompleteCommandException(input[0]);
        }
        String description = input[1];
        return new AddTodoCommand(description);
    }

    /**
     * Parses the user input into a deadline command.
     * @param input User input.
     * @return Deadline command to be executed.
     * @throws IncompleteCommandException If the command is incomplete.
     * @throws NoDeadlineException If the deadline is not provided.
     */
    private static Command parseDeadlineCommand(String[] input) throws IncompleteCommandException {
        if (input.length == 1 || input[1].equals("")) {
            throw new IncompleteCommandException(input[0]);
        }
        input = input[1].split(" /by ", 2);
        if (input.length == 1) {
            throw new NoDeadlineException();
        }
        String description = input[0];
        String by = input[1];
        return new AddDeadlineCommand(description, by);
    }

    /**
     * Parses the user input into an event command.
     * @param input User input.
     * @return Event command to be executed.
     * @throws IncompleteCommandException If the command is incomplete.
     * @throws NoPeriodException If the period is not provided.
     */
    private static Command parseEventCommand(String[] input) throws IncompleteCommandException {
        if (input.length == 1 || input[1].equals("")) {
            throw new IncompleteCommandException(input[0]);
        }
        input = input[1].split(" /from ", 2);
        if (input.length == 1) {
            throw new NoPeriodException();
        }
        String description = input[0];
        String[] arr = input[1].split(" /to ", 2);
        if (input.length == 1) {
            throw new NoPeriodException();
        }
        return new AddEventCommand(description, arr[0], arr[1]);
    }

    /**
     * Parses the user input into a find command.
     * @param input User input.
     * @return Find command to be executed.
     * @throws IncompleteCommandException If the command is incomplete.
     */
    private static Command parseFindCommand(String[] input) throws IncompleteCommandException {
        if (input.length == 1 || input[1].equals("")) {
            throw new IncompleteCommandException(input[0]);
        }
        String description = input[1];
        return new FindCommand(description);
    }
}
