package ezra;

import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Parses user input commands and performs corresponding actions.
 */
public class Parser {
    /**
     * Reads the user input, processes the command, updates the task list, and generates a reply.
     *
     * @param input   The user input command.
     * @param storage Storage object for writing tasks to a file.
     * @param tasks   TaskList object for managing tasks.
     * @return A message to be displayed to the user.
     */
    public static String generateReply(String input, Storage storage, TaskList tasks) {
        try {
            if (input.equals("bye")) {
                return "Bye. Hope to see you again soon!";
            } else if (input.equals("list")) {
                return tasks.listTasks();
            } else if (input.equals("undo")) {
                return tasks.undo(storage);
            } else if (input.startsWith("mark")) {
                return tasks.mark(storage, Parser.parseMark(input));
            } else if (input.startsWith("unmark")) {
                return tasks.unmark(storage, Parser.parseUnmark(input));
            } else if (input.startsWith("delete")) {
                return tasks.delete(storage, Parser.parseDelete(input));
            } else if (input.startsWith("todo")) {
                return tasks.updateTasks(storage, Parser.parseToDo(input));
            } else if (input.startsWith("deadline")) {
                return tasks.updateTasks(storage, Parser.parseDeadline(input));
            } else if (input.startsWith("event")) {
                return tasks.updateTasks(storage, Parser.parseEvent(input));
            } else if (input.startsWith("find")) {
                return tasks.find(Parser.parseFind(input));
            } else {
                return "Invalid command";
            }
        } catch (WrongFormatException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return "Date time must be in this format:\n28/01/2023 1800";
        }
    }

    /**
     * Parses a 'todo' command from the user input.
     *
     * @param input The user input command.
     * @return A ToDo object representing the task to be added.
     * @throws WrongFormatException If the command format is invalid.
     */
    public static ToDo parseToDo(String input) throws WrongFormatException {
        boolean isValidToDoCommand = Pattern.matches("todo\\s\\S.*", input);
        if (!isValidToDoCommand) {
            throw new WrongFormatException("Invalid 'todo' command format. Usage: todo <description>");
        }
        return new ToDo(extractToDoDescription(input));
    }

    /**
     * Extracts the description from a 'todo' command.
     *
     * @param input The user input command.
     * @return The description of the 'todo' task.
     */
    public static String extractToDoDescription(String input) {
        return input.split("\\s", 2)[1];
    }

    /**
     * Parses a 'deadline' command from the user input.
     *
     * @param input The user input command.
     * @return A Deadline object representing the task to be added.
     * @throws WrongFormatException If the command format is invalid.
     */
    public static Deadline parseDeadline(String input) throws WrongFormatException {
        boolean isValidDeadlineCommand = Pattern.matches("deadline\\s\\S.*\\s/by\\s\\S.*", input);
        if (!isValidDeadlineCommand) {
            throw new WrongFormatException(
                    "Invalid 'deadline' command format. Usage: deadline <description> /by <date time>");
        }
        return new Deadline(extractDeadlineDescription(input), extractDeadlineBy(input));
    }

    /**
     * Extracts the description from a 'deadline' command.
     *
     * @param input The user input command.
     * @return The description of the 'deadline' task.
     */
    public static String extractDeadlineDescription(String input) {
        String deadlineSpaceDescription = input.split("\\s/by\\s")[0];
        return deadlineSpaceDescription.split("\\s", 2)[1];
    }

    /**
     * Extracts the deadline from a 'deadline' command.
     *
     * @param input The user input command.
     * @return The deadline of the 'deadline' task.
     */
    public static String extractDeadlineBy(String input) {
        return input.split("\\s/by\\s")[1];
    }

    /**
     * Parses an 'event' command from the user input.
     *
     * @param input The user input command.
     * @return An Event object representing the task to be added.
     * @throws WrongFormatException If the command format is invalid.
     */
    public static Event parseEvent(String input) throws WrongFormatException {
        boolean isValidEventCommand = Pattern.matches("event\\s\\S.*\\s/from\\s\\S.*\\s/to\\s\\S.*", input);
        if (!isValidEventCommand) {
            throw new WrongFormatException(
                    "Invalid 'event' command format. Usage: event <description> /from <date time> /to <date time>");
        }

        return new Event(extractEventDescription(input), extractEventFrom(input), extractEventTo(input));
    }

    /**
     * Extracts the description from an 'event' command.
     *
     * @param input The user input command.
     * @return The description of the 'event' task.
     */
    public static String extractEventDescription(String input) {
        String eventSpaceDescription = input.split("\\s/from\\s")[0];
        return eventSpaceDescription.split("\\s", 2)[1];
    }

    /**
     * Extracts the start datetime from an 'event' command.
     *
     * @param input The user input command.
     * @return The start datetime of the 'event' task.
     */
    public static String extractEventFrom(String input) {
        String inputWithoutTo = input.split("\\s/to\\s")[0];
        return inputWithoutTo.split("\\s/from\\s")[1];
    }

    /**
     * Extracts the end datetime from an 'event' command.
     *
     * @param input The user input command.
     * @return The end datetime of the 'event' task.
     */
    public static String extractEventTo(String input) {
        return input.split("\\s/to\\s")[1];
    }

    /**
     * Parses a 'delete' command from the user input.
     *
     * @param input The user input command.
     * @return The indices of the tasks to be deleted. Invalid index is handled by delete in TaskList.
     * @throws WrongFormatException If the command format is invalid.
     */
    public static int[] parseDelete(String input) throws WrongFormatException {
        boolean isValidDeleteCommand = Pattern.matches("delete(\\s\\d+)+", input);
        if (!isValidDeleteCommand) {
            throw new WrongFormatException("Invalid 'delete' command format. Usage: delete <existing task numbers>");
        }
        return extractTaskIndices(input);
    }

    /**
     * Parses a 'mark' command from the user input.
     *
     * @param input The user input command.
     * @return The indices of the tasks to be marked as done. Invalid index is handled by mark in TaskList.
     * @throws WrongFormatException If the command format is invalid.
     */
    public static int[] parseMark(String input) throws WrongFormatException {
        boolean isValidMarkCommand = Pattern.matches("mark(\\s\\d+)+", input);
        if (!isValidMarkCommand) {
            throw new WrongFormatException("Invalid 'mark' command format. Usage: mark <existing task numbers>");
        }
        return extractTaskIndices(input);
    }

    /**
     * Parses an 'unmark' command from the user input.
     *
     * @param input The user input command.
     * @return The indices of the tasks to be marked as not done. Invalid index is handled by unmark in TaskList.
     * @throws WrongFormatException If the command format is invalid.
     */
    public static int[] parseUnmark(String input) throws WrongFormatException {
        boolean isValidUnmarkCommand = Pattern.matches("unmark(\\s\\d+)+", input);
        if (!isValidUnmarkCommand) {
            throw new WrongFormatException("Invalid 'unmark' command format. Usage: unmark <existing task numbers>");
        }
        return extractTaskIndices(input);
    }

    /**
     * Extracts task indices from a 'delete', 'mark', or 'unmark' command.
     *
     * @param input The user input command.
     * @return The indices of the tasks to be deleted.
     */
    public static int[] extractTaskIndices(String input) {
        String[] splitArray = input.split("\\s");
        // First element in splitArray is the name of the command
        String[] taskNumbers = Arrays.copyOfRange(splitArray, 1, splitArray.length);
        return Arrays.stream(taskNumbers).mapToInt(x -> Integer.parseInt(x) - 1).toArray();
    }

    /**
     * Parses a 'find' command from the user input and extracts the keyword to search for.
     *
     * @param input The user input command.
     * @return The keyword to search for in task descriptions.
     * @throws WrongFormatException If the command format is invalid.
     */
    public static String parseFind(String input) throws WrongFormatException {
        boolean isValidFindCommand = Pattern.matches("find\\s\\S.*", input);
        if (!isValidFindCommand) {
            throw new WrongFormatException("Invalid 'find' command format. Usage: find <keyword>");
        }
        return extractKeyword(input);
    }

    /**
     * Extracts the keyword from a 'find' command.
     *
     * @param input The user input command.
     * @return The keyword to search for in task descriptions.
     */
    public static String extractKeyword(String input) {
        return input.split("\\s", 2)[1];
    }
}
