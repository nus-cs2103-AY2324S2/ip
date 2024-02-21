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

    /**
     * Parses a "mark" command from its string representation.
     *
     * @param parts An array of strings representing the command with potentially the task index in parts[1]
     * @return A new {@code MarkCommand} object if the command is valid
     * @throws DukeException if the command format is invalid or the task index is not a number
     */
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


    /**
     * Parses an "unmark" command from its string representation.
     *
     * @param parts An array of strings representing the command with potentially the task index in parts[1]
     * @return A new {@code UnmarkCommand} object if the command is valid
     * @throws DukeException if the command format is invalid or the task index is not a number
     */
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

    /**
     * Parses a "delete" command from its string representation.
     *
     * @param parts An array of strings representing the command with potentially the task index in parts[1]
     * @return A new {@code DeleteCommand} object if the command is valid
     * @throws DukeException if the command format is invalid, the command is empty, or the task index is not a number
     */
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

    /**
     * Parses a "deadline" command from its string representation. Extracts the task description and
     * deadline information to create a Deadline object.
     *
     * @param parts The string containing the deadline command, potentially split into parts
     * @return A new {@code AddCommand} object encapsulating a {@code Deadline} task
     * @throws DukeException if the command format is invalid or the task creation fails
     */
    private static Command parseDeadlineCommand(String parts) throws DukeException {
        assert parts != null : "parts should not be null";
        String[] parts2 = splitInput(parts, " /by ", "deadline");
        String description = getDescription(parts2[0], "deadline");
        String by = parts2[1].trim();
        Task task;
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

    /**
     * Parses an "event" command from its string representation. Extracts the task description,
     * start, and end date/time information to create an Event object.
     *
     * @param parts The string containing the event command, potentially split into parts
     * @return A new {@code AddCommand} object encapsulating an {@code Event} task
     * @throws DukeException if the command format is invalid or the task creation fails
     */
    private static Command parseEventCommand(String parts) throws DukeException {
        // Split parts[1] to extract description and date/time, and
        assert parts != null: "parts should not be null";
        String[] parts2 = splitInput(parts, " /from ", "event");
        String description = getDescription(parts2[0], "event");

        String[] timeParts = splitInput(parts2[1], " /to ", "event");
        String from = timeParts[0].trim();
        String to = timeParts[1].trim();
        Task task;
        try {
            task = new Event(description, from, to);
            assert task != null : "Event task should not be null";
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }
        if (task == null) {
            throw new DukeException("Please use 'todo', 'deadline', or 'event' to create tasks.");
        }
        return new AddCommand(task);
    }

    /**
     * Parses a "tasks" command, specifically for listing tasks on a given date.
     *
     * @param tasks The {@code TaskList} object to manage tasks
     * @param parts The string containing the tasks command, potentially split into parts
     * @return A new {@code ListTasksOnDateCommand} if the command format is valid
     * @throws DukeException if the command format is invalid
     */
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

    /**
     * Splits the input string based on a delimiter and ensures the format is correct for the command.
     *
     * @param input The raw input string.
     * @param delimiter The delimiter to split the string.
     * @param command The type of command (for error messages).
     * @return An array of split parts.
     * @throws DukeException If the input format is invalid.
     */
    private static String[] splitInput(String input, String delimiter, String command) throws DukeException {
        String[] parts = input.split(delimiter);
        CommandType commandType = CommandType.fromString(command);
        if (parts.length != 2 || parts[0].trim().length() <= command.length()) {
            switch(commandType) {
                case DEADLINE:
                    throw new DukeException("Invalid deadline format. Please use the following format:\n"
                        + "     deadline <description> /by <time>");
                case EVENT:
                    throw new DukeException("Invalid event format. Please use the following format:\n     event <description> /from <start time> /to <end time>");
                    // Add more cases as needed
                default:
                    throw new DukeException("Parsing error: Unrecognized command type");
            }
        }
        return parts;
    }

    /**
     * Extracts the description from the input part after removing the command type.
     *
     * @param part The part of the input containing the description.
     * @param command The type of command (to remove from the beginning).
     * @return The description extracted from the input part.
     */
    private static String getDescription(String part, String command) {
        return part.trim().substring(command.length()).trim();
    }
}