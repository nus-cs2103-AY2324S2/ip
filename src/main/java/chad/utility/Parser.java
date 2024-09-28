package chad.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import chad.command.AddCommand;
import chad.command.ArchiveCommand;
import chad.command.Command;
import chad.command.DeleteCommand;
import chad.command.ExitCommand;
import chad.command.FindCommand;
import chad.command.ListCommand;
import chad.command.MarkCommand;
import chad.command.UnmarkCommand;
import chad.exceptions.ChadException;
import chad.task.Deadline;
import chad.task.Event;
import chad.task.Task;
import chad.task.Todo;

/**
 * Parses inputs to Chad.
 */
public class Parser {
    private static final int DEADLINE_LENGTH = 10;
    private static final int EVENT_LENGTH = 6;
    private static final int TODO_LENGTH = 6;
    private static final int FROM_LENGTH = 6;
    private static final int TO_LENGTH = 4;
    private static final int BY_LENGTH = 4;
    private static final int MARK_MINIMUM = 6;
    private static final int UNMARK_MINIMUM = 8;
    private static final int DELETE_MINIMUM = 8;
    private static final int FILE_TASK_INDEX = 7;
    private static final int FILE_BY_LENGTH = 5;
    private static final int FILE_FROM_LENGTH = 7;
    private static final int FILE_TO_LENGTH = 4;
    private static final int TASK_POS = 1;
    private static final int MARK_POS = 4;
    private static Event createEvent(String s) throws ChadException {
        int fromIndex = s.indexOf("/from");
        int toIndex = s.indexOf("/to");

        if (fromIndex == -1 || toIndex == -1 || s.length() < EVENT_LENGTH + 1) {
            throw new ChadException("Format Error, Event must be in format:"
                    + "Event /from YYYY-MM-DD HHmm /to YYYY-MM-DD HHmm");
        }

        assert s.length() > EVENT_LENGTH;
        String eventName = s.substring(EVENT_LENGTH, fromIndex - 1);
        try {
            String fromDate = s.substring(fromIndex + FROM_LENGTH, toIndex - 1);
            String toDate = s.substring(toIndex + TO_LENGTH);
            return new Event(eventName, fromDate, toDate);
        } catch (StringIndexOutOfBoundsException | DateTimeParseException e) {
            throw new ChadException("Format Error, Event must be in format:"
                    + "Event /from YYYY-MM-DD HHmm /to YYYY-MM-DD HHmm");
        }
    }

    private static Deadline createDeadline(String s) throws ChadException {
        int byIndex = s.indexOf("/by");

        if (byIndex == -1 || s.length() < DEADLINE_LENGTH) {
            throw new ChadException("Format Error, Deadline must be in format: Deadline /by YYYY-MM-DD HHmm");
        }

        assert s.length() > DEADLINE_LENGTH;
        String deadlineName = s.substring(DEADLINE_LENGTH - 1, byIndex - 1);
        try {
            String deadlineDate = s.substring(byIndex + BY_LENGTH);
            return new Deadline(deadlineName, deadlineDate);
        } catch (StringIndexOutOfBoundsException | DateTimeParseException e) {
            throw new ChadException("Format Error, Deadline must be in format: Deadline /by YYYY-MM-DD HHmm");
        }
    }

    private static Todo createTodo(String s) throws ChadException {
        if (s.length() < TODO_LENGTH) {
            throw new ChadException("Error, description of todo is missing");
        }
        assert s.length() > 5;
        String name = s.substring(5);
        return new Todo(name);
    }

    /**
     * Parses a line of input to Chad.
     *
     * @param command the line of input
     * @return Command to execute
     * @throws ChadException if input is incorrect.
     */
    public static Command parse(String command) throws ChadException {
        String[] splitCommand = command.split(" ");
        switch (splitCommand[0]) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            if (command.length() < MARK_MINIMUM) {
                throw new ChadException("Task not indicated");
            }
            return new MarkCommand(Integer.parseInt(splitCommand[1]));
        case "unmark":
            if (command.length() < UNMARK_MINIMUM) {
                throw new ChadException("Task not indicated");
            }
            return new UnmarkCommand(Integer.parseInt(splitCommand[1]));
        case "delete":
            if (command.length() < DELETE_MINIMUM) {
                throw new ChadException("Task not indicated");
            }
            return new DeleteCommand(Integer.parseInt(splitCommand[1]));
        case "todo":
            return new AddCommand(createTodo(command));
        case "deadline":
            return new AddCommand(createDeadline(command));
        case "event":
            return new AddCommand(createEvent(command));
        case "find":
            if (splitCommand.length > 2) {
                throw new ChadException("Please find one keyword at a time.");
            }
            return new FindCommand(splitCommand[1]);
        case "archive":
            return new ArchiveCommand();
        default:
            throw new ChadException("Unknown Command");
        }
    }

    protected static String formatDate(String s) throws ChadException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy HHmm");
            LocalDateTime date = LocalDateTime.parse(s, formatter);
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return date.format(formatter);
        } catch (DateTimeParseException e) {
            throw new ChadException("File is of wrong format.");
        }
    }

    /**
     * Parses a line in a file with a task list.
     *
     * @param s the line of input from a file
     * @return A task.
     * @throws ChadException if task list is in the wrong format.
     */
    public static Task parseFile(String s) throws ChadException {
        Task t;
        if (s.charAt(TASK_POS) == 'T') { //TODO
            t = new Todo(s.substring(FILE_TASK_INDEX));
        } else if (s.charAt(TASK_POS) == 'D') { //DEADLINE
            int byIndex = s.indexOf("(by");

            if (byIndex == -1) {
                throw new ChadException("File is of wrong format.");
            }

            String deadlineName = s.substring(FILE_TASK_INDEX, byIndex - 1);
            String deadlineBy = s.substring(byIndex + FILE_BY_LENGTH, s.length() - 1);
            t = new Deadline(deadlineName, formatDate(deadlineBy));
        } else if (s.charAt(TASK_POS) == 'E') { //EVENT
            int fromIndex = s.indexOf("(from:");
            int toIndex = s.indexOf("to:");

            if (fromIndex == -1 || toIndex == -1) {
                throw new ChadException("File is of wrong format.");
            }

            String eventName = s.substring(FILE_TASK_INDEX, fromIndex - 1);
            String eventFrom = s.substring(fromIndex + FILE_FROM_LENGTH, toIndex - 1);
            String eventTo = s.substring(toIndex + FILE_TO_LENGTH, s.length() - 1);
            t = new Event(eventName, formatDate(eventFrom), formatDate(eventTo));
        } else {
            throw new ChadException("File is of wrong format.");
        }

        assert t != null;
        if (s.charAt(MARK_POS) == 'X') {
            t.setDone(true);
        } else if (s.charAt(MARK_POS) == ' ') {
            t.setDone(false);
        } else {
            throw new ChadException("File is of wrong format.");
        }

        return t;
    }
}
