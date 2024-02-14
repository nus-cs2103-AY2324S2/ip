package duke.utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
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
    private static Event createEvent(String s) throws DukeException {
        int fromIndex = s.indexOf("/from");
        int toIndex = s.indexOf("/to");

        if (fromIndex == -1 || toIndex == -1 || s.length() < EVENT_LENGTH + 1) {
            throw new DukeException("Format Error, Event must be in format:"
                    + "Event /from YYYY-MM-DD HHmm /to YYYY-MM-DD HHmm");
        }

        String eventName = s.substring(EVENT_LENGTH, fromIndex - 1);

        try {
            String fromDate = s.substring(fromIndex + FROM_LENGTH, toIndex - 1);
            String toDate = s.substring(toIndex + TO_LENGTH);
            return new Event(eventName, fromDate, toDate);
        } catch (StringIndexOutOfBoundsException | DateTimeParseException e) {
            throw new DukeException("Format Error, Event must be in format:"
                    + "Event /from YYYY-MM-DD HHmm /to YYYY-MM-DD HHmm");
        }
    }

    private static Deadline createDeadline(String s) throws DukeException {
        int byIndex = s.indexOf("/by");

        if (byIndex == -1 || s.length() < DEADLINE_LENGTH) {
            throw new DukeException("Format Error, Deadline must be in format: Deadline /by YYYY-MM-DD HHmm");
        }

        String deadlineName = s.substring(DEADLINE_LENGTH - 1, byIndex - 1);

        try {
            String deadlineDate = s.substring(byIndex + BY_LENGTH);
            return new Deadline(deadlineName, deadlineDate);
        } catch (StringIndexOutOfBoundsException | DateTimeParseException e) {
            throw new DukeException("Format Error, Deadline must be in format: Deadline /by YYYY-MM-DD HHmm");
        }
    }

    private static Todo createTodo(String s) throws DukeException {
        if (s.length() < TODO_LENGTH) {
            throw new DukeException("Error, description of todo is missing");
        }
        String name = s.substring(5);
        return new Todo(name);
    }

    /**
     * Parses a line of input to Duke.
     *
     * @param command the line of input
     * @return Command.
     * @throws DukeException if input is incorrect.
     */
    public static Command parse(String command) throws DukeException {
        String[] splitCommand = command.split(" ");
        switch (splitCommand[0]) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            if (command.length() < MARK_MINIMUM) {
                throw new DukeException("Task not indicated");
            }
            return new MarkCommand(Integer.parseInt(splitCommand[1]));
        case "unmark":
            if (command.length() < UNMARK_MINIMUM) {
                throw new DukeException("Task not indicated");
            }
            return new UnmarkCommand(Integer.parseInt(splitCommand[1]));
        case "delete":
            if (command.length() < DELETE_MINIMUM) {
                throw new DukeException("Task not indicated");
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
                throw new DukeException("Please find one keyword at a time.");
            }
            return new FindCommand(splitCommand[1]);
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
        if (s.charAt(TASK_POS) == 'T') { //TODO
            t = new Todo(s.substring(FILE_TASK_INDEX));
        } else if (s.charAt(TASK_POS) == 'D') { //DEADLINE
            int byIndex = s.indexOf("(by");

            if (byIndex == -1) {
                throw new DukeException("File is of wrong format.");
            }

            String deadlineName = s.substring(FILE_TASK_INDEX, byIndex - 1);
            String deadlineBy = s.substring(byIndex + FILE_BY_LENGTH, s.length() - 1);
            t = new Deadline(deadlineName, formatDate(deadlineBy));
        } else if (s.charAt(TASK_POS) == 'E') { //EVENT
            int fromIndex = s.indexOf("(from:");
            int toIndex = s.indexOf("to:");

            if (fromIndex == -1 || toIndex == -1) {
                throw new DukeException("File is of wrong format.");
            }

            String eventName = s.substring(FILE_TASK_INDEX, fromIndex - 1);
            String eventFrom = s.substring(fromIndex + FILE_FROM_LENGTH, toIndex - 1);
            String eventTo = s.substring(toIndex + FILE_TO_LENGTH, s.length() - 1);
            t = new Event(eventName, formatDate(eventFrom), formatDate(eventTo));
        } else {
            throw new DukeException("File is of wrong format.");
        }

        if (s.charAt(MARK_POS) == 'X') {
            t.setDone(true);
        } else if (s.charAt(MARK_POS) == ' ') {
            t.setDone(false);
        } else {
            throw new DukeException("File is of wrong format.");
        }

        return t;
    }
}
