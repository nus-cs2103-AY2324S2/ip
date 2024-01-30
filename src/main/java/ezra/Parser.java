package ezra;

import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

/**
 * Parses user input commands and performs corresponding actions.
 */
public class Parser {

    /**
     * Reads the user input, processes the command, and updates the task list.
     *
     * @param input   The user input command.
     * @param storage Storage object for writing tasks to a file.
     * @param tasks   TaskList object for managing tasks.
     */
    public static void read(String input, Storage storage, TaskList tasks) {
        Ui.horizontalLine();
        try {
            if (input.equals("bye")) {
                System.out.println("\tBye. Hope to see you again soon!");
            } else if (input.equals("list")) {
                tasks.listTasks();
            } else if (input.startsWith("mark")) {
                tasks.mark(Parser.parseMark(input), storage);
            } else if (input.startsWith("unmark")) {
                tasks.unmark(Parser.parseUnmark(input), storage);
            } else if (input.startsWith("delete")) {
                tasks.delete(Parser.parseDelete(input), storage);
            } else if (input.startsWith("todo")) {
                tasks.updateTasks(Parser.parseToDo(input), storage);
            } else if (input.startsWith("deadline")) {
                tasks.updateTasks(Parser.parseDeadline(input), storage);
            } else if (input.startsWith("event")) {
                tasks.updateTasks(Parser.parseEvent(input), storage);
            } else {
                System.out.println("\tInvalid command");
            }
        } catch (WrongFormatException e) {
            System.out.println("\t" + e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("\tDate time must be in this format: 28/01/2023 1800");
        }
        Ui.horizontalLine();
    }

    /**
     * Parses a 'todo' command from the user input.
     *
     * @param input The user input command.
     * @return A ToDo object representing the task to be added.
     * @throws WrongFormatException If the command format is invalid.
     */
    public static ToDo parseToDo(String input) throws WrongFormatException {
        if (Pattern.matches("todo\\s\\S.*", input)) {
            String description = input.split("\\s", 2)[1];
            return new ToDo(description);
        } else {
            throw new WrongFormatException(
                    "Invalid 'todo' command format. Usage: todo <description>"
            );
        }
    }

    /**
     * Parses a 'deadline' command from the user input.
     *
     * @param input The user input command.
     * @return A Deadline object representing the task to be added.
     * @throws WrongFormatException If the command format is invalid.
     */
    public static Deadline parseDeadline(String input) throws WrongFormatException {
        if (Pattern.matches("deadline\\s\\S.*\\s/by\\s\\S.*", input)) {
            String[] arr = input.split("\\s/by\\s");
            String by = arr[1];
            String description = arr[0].split("\\s", 2)[1];
            return new Deadline(description, by);
        } else {
            throw new WrongFormatException(
                    "Invalid 'deadline' command format. Usage: deadline <description> /by <date time>"
            );
        }
    }

    /**
     * Parses an 'event' command from the user input.
     *
     * @param input The user input command.
     * @return An Event object representing the task to be added.
     * @throws WrongFormatException If the command format is invalid.
     */
    public static Event parseEvent(String input) throws WrongFormatException {
        if (Pattern.matches("event\\s\\S.*\\s/from\\s\\S.*\\s/to\\s\\S.*", input)) {
            String[] splitTo = input.split("\\s/to\\s");
            String to = splitTo[1];
            String[] splitFrom = splitTo[0].split("\\s/from\\s");
            String from = splitFrom[1];
            String description = splitFrom[0].split("\\s", 2)[1];
            return new Event(description, from, to);
        } else {
            throw new WrongFormatException(
                    "Invalid 'event' command format. Usage: event <description> /from <date time> /to <date time>"
            );
        }
    }

    /**
     * Parses a 'delete' command from the user input.
     *
     * @param input The user input command.
     * @return The index of the task to be deleted. Invalid index is handled by delete in TaskList.
     * @throws WrongFormatException If the command format is invalid.
     */
    public static int parseDelete(String input) throws WrongFormatException {
        if (Pattern.matches("delete\\s\\d+", input)) {
            return Integer.parseInt(input.split("\\s")[1]) - 1;
        } else {
            throw new WrongFormatException(
                    "Invalid 'delete' command format. Usage: delete <existing task number>"
            );
        }
    }

    /**
     * Parses a 'mark' command from the user input.
     *
     * @param input The user input command.
     * @return The index of the task to be marked as done. Invalid index is handled by mark in TaskList.
     * @throws WrongFormatException If the command format is invalid.
     */
    public static int parseMark(String input) throws WrongFormatException {
        if (Pattern.matches("mark\\s\\d+", input)) {
            return Integer.parseInt(input.split("\\s")[1]) - 1;
        } else {
            throw new WrongFormatException(
                    "Invalid 'mark' command format. Usage: mark <existing task number>"
            );
        }
    }

    /**
     * Parses an 'unmark' command from the user input.
     *
     * @param input The user input command.
     * @return The index of the task to be marked as not done. Invalid index is handled by unmark in TaskList.
     * @throws WrongFormatException If the command format is invalid.
     */
    public static int parseUnmark(String input) throws WrongFormatException {
        if (Pattern.matches("unmark\\s\\d+", input)) {
            return Integer.parseInt(input.split("\\s")[1]) - 1;
        } else {
            throw new WrongFormatException(
                    "Invalid 'unmark' command format. Usage: unmark <existing task number>"
            );
        }
    }
}
