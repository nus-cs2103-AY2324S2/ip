package duke.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.ArchiveCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.exceptions.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Parses inputs to Duke.
 */
public class Parser {
    private static Event createEvent(String s) throws DukeException {
        int fromIndex = s.indexOf("/from");
        int toIndex = s.indexOf("/to");
        if (fromIndex == -1 || toIndex == -1 || s.length() < 7) {
            throw new DukeException("Format Error, Event must be in format:"
                    + "Event /from YYYY-MM-DD HHmm /to YYYY-MM-DD HHmm");
        }
        String eventName = s.substring(6, fromIndex - 1);
        try {
            String from = s.substring(fromIndex + 6, toIndex - 1);
            String to = s.substring(toIndex + 4);
            return new Event(eventName, from, to);
        } catch (StringIndexOutOfBoundsException | DateTimeParseException e) {
            throw new DukeException("Format Error, Event must be in format:"
                    + "Event /from YYYY-MM-DD HHmm /to YYYY-MM-DD HHmm");
        }
    }

    private static Deadline createDeadline(String s) throws DukeException {
        int byIndex = s.indexOf("/by");
        if (byIndex == -1 || s.length() < 10) {
            throw new DukeException("Format Error, Deadline must be in format: Deadline /by YYYY-MM-DD HHmm");
        }
        String deadlineName = s.substring(9, byIndex - 1);
        try {
            String deadlineBy = s.substring(byIndex + 4);
            return new Deadline(deadlineName, deadlineBy);
        } catch (StringIndexOutOfBoundsException | DateTimeParseException e) {
            throw new DukeException("Format Error, Deadline must be in format: Deadline /by YYYY-MM-DD HHmm");
        }
    }

    private static Todo createTodo(String s) throws DukeException {
        if (s.length() < 6) {
            throw new DukeException("Error, description of todo is missing");
        }
        String name = s.substring(5);
        return new Todo(name);
    }

    /**
     * Parses a line of input to Duke.
     *
     * @param command the line of input
     * @return Command to execute
     * @throws DukeException if input is incorrect.
     */
    public static Command parse(String command) throws DukeException {
        String[] s = command.split(" ");
        switch (s[0]) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            if (command.length() < 6) {
                throw new DukeException("Task not indicated");
            }
            return new MarkCommand(Integer.parseInt(s[1]));
        case "unmark":
            if (command.length() < 8) {
                throw new DukeException("Task not indicated");
            }
            return new UnmarkCommand(Integer.parseInt(s[1]));
        case "delete":
            if (command.length() < 8) {
                throw new DukeException("Task not indicated");
            }
            return new DeleteCommand(Integer.parseInt(s[1]));
        case "todo":
            return new AddCommand(createTodo(command));
        case "deadline":
            return new AddCommand(createDeadline(command));
        case "event":
            return new AddCommand(createEvent(command));
        case "find":
            if (s.length > 2) {
                throw new DukeException("Please find one keyword at a time");
            }
            return new FindCommand(s[1]);
        case "archive":
            return new ArchiveCommand();
        default:
            throw new DukeException("Unknown Command");
        }
    }

    protected static String formatDate(String s) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy HHmm");
            LocalDateTime date = LocalDateTime.parse(s, formatter);
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return date.format(formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("File is of wrong format.");
        }
    }

    /**
     * Parses a line in a file with a task list.
     *
     * @param s the line of input from a file
     * @return A task.
     * @throws DukeException if task list is in the wrong format.
     */
    public static Task parseFile(String s) throws DukeException {
        Task t;
        if (s.charAt(1) == 'T') {
            t = new Todo(s.substring(7));
        } else if (s.charAt(1) == 'D') {
            int byIndex = s.indexOf("(by");
            if (byIndex == -1) {
                throw new DukeException("File is of wrong format.");
            }
            String deadlineName = s.substring(7, byIndex - 1);
            String deadlineBy = s.substring(byIndex + 5, s.length() - 1);
            t = new Deadline(deadlineName, formatDate(deadlineBy));
        } else if (s.charAt(1) == 'E') {
            int fromIndex = s.indexOf("(from:");
            int toIndex = s.indexOf("to:");
            if (fromIndex == -1 || toIndex == -1) {
                throw new DukeException("File is of wrong format.");
            }
            String eventName = s.substring(7, fromIndex - 1);
            String eventFrom = s.substring(fromIndex + 7, toIndex - 1);
            String eventTo = s.substring(toIndex + 4, s.length() - 1);
            t = new Event(eventName, formatDate(eventFrom), formatDate(eventTo));
        } else {
            throw new DukeException("File is of wrong format.");
        }

        if (s.charAt(4) == 'X') {
            t.setDone(true);
        } else if (s.charAt(4) == ' ') {
            t.setDone(false);
        } else {
            throw new DukeException("File is of wrong format.");
        }

        return t;
    }
}
