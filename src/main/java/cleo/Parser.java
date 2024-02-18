package cleo;
import command.Command;
import command.AddCommand;
import command.DeleteCommand;
import command.ListCommand;
import command.ExitCommand;
import command.ListTasksOnDateCommand;
import command.MarkCommand;
import command.UnMarkCommand;
import command.FindCommand;

/**
 * The Parser class is responsible for interpreting user input and translating it into commands.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding Command object.
     *
     * @param input The user input string.
     * @return Command object representing the user's command.
     * @throws DukeException if the input is invalid or cannot be parsed.
     */
    private static UI ui = new UI();
    public static Command parse(TaskList tasks, String input) throws DukeException {
        String[] parts = input.split(" ", 3);
        String command = parts[0];
        CommandType commandType = CommandType.fromString(command);
        switch (commandType) {
            case BYE:
                return new ExitCommand();
            case LIST:
                return new ListCommand();
            case MARK:
                return parseMarkCommand(parts);
            case UNMARK:
                return parseUnmarkCommand(parts);
            case DELETE:
                return parseDeleteCommand(parts);
            case TODO:
                return new AddCommand(new Todo(input.substring(5)));
            case DEADLINE:
                return parseDeadlineCommand(input);
            case EVENT:
                return parseEventCommand(input);
            case TASKS:
                return parseTasksCommand(tasks, parts);
            case FIND:
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new DukeException("The description of a find command cannot be empty.");
                }
                String[] keywords = input.substring(input.indexOf(" ") + 1).trim().split("\\s+");
                return new FindCommand(keywords);
            case UNKNOWN:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            default:
                throw new DukeException("Unexpected Command Type" + "\n"
                        + "Please enter a valid command (bye, list, mark, unmark, delete, todo, event, deadline, tasks on)");
        }
    }

    private static Command parseMarkCommand(String[] parts) throws DukeException {
        // Implementation for parsing mark command
        // Ensure parts[1] is a valid task index and return new MarkCommand(index)
        assert parts != null: "parts should not be null";
        try {
            if (parts.length == 2) {
                return new MarkCommand(Integer.parseInt(parts[1]) - 1);
            } else {
                throw new DukeException("☹ OOPS!!! The description of a mark command should be in the format:\n"
                        + "     mark <task number>");
            }
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        } catch (NumberFormatException e) {
            // Handle case where the part after unmark is not a number
            throw new DukeException("Invalid task number. Please enter a number.");
        }
    }

    private static Command parseUnmarkCommand(String[] parts) throws DukeException {
        // Similar to parseMarkCommand
        assert parts != null: "parts should not be null";
        try {
            if (parts.length == 2) {
                return new UnMarkCommand(Integer.parseInt(parts[1]) - 1);
            } else {
                throw new DukeException("☹ OOPS!!! The description of an unmark command should be in the format:\n"
                        + "     unmark <task number>");
            }
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        } catch (NumberFormatException e) {
            // Handle case where the part after unmark is not a number
            throw new DukeException("Invalid task number. Please enter a number.");
        }
    }

    private static Command parseDeleteCommand(String[] parts) throws DukeException {
        // Ensure parts[1] is a valid task index and return new DeleteCommand(index)
        try {
            if (parts.length == 1) {
                throw new DukeException("☹ OOPS!!! The description of a delete command cannot be empty.");
            } else if (parts.length != 2) {
                throw new DukeException("Invalid delete format. Please use the following format:\n"
                        + "     delete <task number>");
            }
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            return new DeleteCommand(taskIndex);
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        } catch (NumberFormatException e) {
            // Handle case where the part after delete is not a number
            throw new DukeException("Invalid task number. Please enter a number.");
        }
    }

    private static Command parseDeadlineCommand(String parts) throws DukeException {
        // Split parts[1] to extract description and date, and return new AddCommand(new Deadline(...))
        assert parts != null: "parts should not be null";
        String[] parts2 = parts.split(" /by ");
        Task task = null;
        if (parts2.length != 2 || parts2[0].length() <= 9) { // Check for correct format and description length
            throw new DukeException("Invalid deadline format. Please use the following format:\n"
                    + "     deadline <description> /by <time>");
        }
        String description = parts2[0].substring(9); // Assuming "deadline " is 9 characters long
        String by = parts2[1];
        try {
            task = new Deadline(description, by);
            assert task != null : "Deadline task should not be null";
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
        if (task == null) {
            throw new DukeException("Please use 'todo', 'deadline', or 'event' to create tasks.");
        }
        return new AddCommand(task);
    }

    private static Command parseEventCommand(String parts) throws DukeException {
        // Split parts[1] to extract description and date/time, and
        assert parts != null: "parts should not be null";
        String[] parts2 = parts.split(" /from ");
        Task task = null;
        if (parts2.length != 2) {
            throw new DukeException("Invalid deadline format. Please use the following format:\n"
                    + "      event <description> /from <start time> /to <end time>");
        }
        String[] partsWithTo = parts2[1].split(" /to ");
        if (partsWithTo.length != 2) {
            throw new DukeException("Invalid event format. Please use the following format:\n"
                    + "     event <description> /from <start time> /to <end time>");
        }
        String eventDescription = parts2[0].substring(6);
        try {
            task = new Event(eventDescription, partsWithTo[0], partsWithTo[1]);
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
        if (task == null) {
            throw new DukeException("Please use 'todo', 'deadline', or 'event' to create tasks.");
        }
        return new AddCommand(task);
    }

    private static Command parseTasksCommand(TaskList tasks, String[] parts) throws DukeException {
        assert parts != null: "parts should not be null";
        if (parts.length == 3 && parts[1].toLowerCase().equals("on")) {
            String dateInput = parts[2];
            return new ListTasksOnDateCommand(dateInput);
        } else {
            throw new DukeException("     Invalid command. Please use the following format:\n"
                        + "     tasks on dd-MM-YYYY");
        }
    }
}