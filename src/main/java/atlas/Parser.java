package atlas;

import atlas.command.*;
import atlas.exception.AtlasException;
import atlas.exception.InvalidDeadlineFormatException;
import atlas.exception.InvalidEventFormatException;
import atlas.task.Deadline;
import atlas.task.Event;
import atlas.task.Task;
import atlas.task.ToDo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * The Parser class is responsible for parsing user input and converting it into executable commands.
 */
public class Parser {
    /**
     * Parses a user input string and returns an executable Command.
     *
     * @param input   The user input string to parse.
     * @param tasks   The TaskList object used for task operations.
     * @param ui      The Ui object used for user interaction.
     * @param storage The Storage object used for file operations.
     * @return Command The command that can be executed based on user input.
     * @throws AtlasException If the input string does not correspond to a valid command format.
     */
    public static Command parse(String input, TaskList tasks, Ui ui, Storage storage) throws AtlasException {
        String[] parts = input.split(" ", 2);
        assert parts.length > 0 : "Input command should not be empty";
        String command = parts[0];
        String details = parts.length > 1 ? parts[1] : null;

        switch (command) {
        case "bye":
            return new ExitCommand(tasks, ui, storage);
        case "list":
            return new ListCommand(tasks, ui, storage);
        case "mark":
            return createMarkCommand(tasks, ui, storage, details);
        case "unmark":
            return createUnmarkCommand(tasks, ui, storage, details);
        case "delete":
            return createDeleteCommand(tasks, ui, storage, details);
        case "todo":
            return new AddToDoCommand(tasks, ui, storage, details);
        case "deadline":
            return createAddDeadlineCommand(tasks, ui, storage, details);
        case "event":
            return createAddEventCommand(tasks, ui, storage, details);
        case "tasks_on":
            return createTasksOnDateCommand(tasks, ui, storage, details);
        case "find":
            return new FindCommand(tasks, ui, storage, details);
        default:
            return new InvalidCommand(tasks, ui, storage);
        }
    }

    private static TasksOnDateCommand createTasksOnDateCommand(TaskList tasks, Ui ui, Storage storage, String details) throws AtlasException {
        LocalDate date;
        try {
            date = LocalDate.parse(details, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            throw new AtlasException("Invalid date format. Please use 'yyyy-MM-dd HHmm'.");
        }
        return new TasksOnDateCommand(tasks, ui, storage, date);
    }

    private static AddEventCommand createAddEventCommand(TaskList tasks, Ui ui, Storage storage, String details) throws AtlasException {
        String[] eventParts = details.split(" /from | /to "); // Split by both "/from" and "/to"
        if (eventParts.length != 3) {
            throw new AtlasException("Invalid event format."
                    + " Please use 'event [description] /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm'.");
        }
        String description = eventParts[0];
        LocalDateTime start;
        LocalDateTime end;
        try {
            start = LocalDateTime.parse(eventParts[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            end = LocalDateTime.parse(eventParts[2], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            throw new InvalidEventFormatException("Invalid date format for event. Please use 'yyyy-MM-dd HHmm'.");
        }
        return new AddEventCommand(tasks, ui, storage, description, start, end);
    }

    private static AddDeadlineCommand createAddDeadlineCommand(TaskList tasks, Ui ui, Storage storage, String details) throws InvalidDeadlineFormatException {
        String[] deadlineDetails = details.split(" /by ");
        LocalDateTime by;
        try {
            by = LocalDateTime.parse(deadlineDetails[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            throw new InvalidDeadlineFormatException("Invalid date/time format. Please use 'yyyy-MM-dd HHmm'.\"");
        }
        return new AddDeadlineCommand(tasks, ui, storage, deadlineDetails[0], by);
    }

    private static DeleteCommand createDeleteCommand(TaskList tasks, Ui ui, Storage storage, String details) {
        int taskIndex = Integer.parseInt(details) - 1;
        return new DeleteCommand(tasks, ui, storage, taskIndex);
    }

    private static UnmarkCommand createUnmarkCommand(TaskList tasks, Ui ui, Storage storage, String details) {
        int taskIndex = Integer.parseInt(details) - 1;
        return new UnmarkCommand(tasks, ui, storage, taskIndex);
    }

    private static MarkCommand createMarkCommand(TaskList tasks, Ui ui, Storage storage, String details) {
        int taskIndex = Integer.parseInt(details) - 1;
        return new MarkCommand(tasks, ui, storage, taskIndex);
    }

    /**
     * Converts a string from file format back into a Task object.
     *
     * @param line The line from the file to be parsed into a Task.
     * @return Task The task created from a single file line.
     */
    public static Task parseLineToTask(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        switch (type) {
        case "T":
            return parseLineToToDo(description, isDone);
        case "D":
            return parseLineToDeadline(parts, description, isDone);
        case "E":
            return parseLineToEvent(parts, description, isDone);
        default:
            assert false : "Line saved in data file cannot be parsed into task";
            return null;
        }
    }

    private static Event parseLineToEvent(String[] parts, String description, boolean isDone) {
        try {
            LocalDateTime start = LocalDateTime.parse(parts[3].trim());
            assert start != null : "start should not be null";
            LocalDateTime end = LocalDateTime.parse(parts[4].trim());
            assert end != null : "end should not be null";
            Event event = new Event(description, start, end);
            assert event != null : "Task object should not be null";
            if (isDone) {
                event.toggle();
            }
            return event;
        } catch (DateTimeParseException e) {
            System.err.println("Failed to parse event dates: " + parts[3] + " and " + parts[4]);
            return null;
        }
    }

    private static Deadline parseLineToDeadline(String[] parts, String description, boolean isDone) {
        try {
            LocalDateTime by = LocalDateTime.parse(parts[3].trim());
            assert by != null : "by should not be null";
            Deadline deadline = new Deadline(description, by);
            assert deadline != null : "Task object should not be null";
            if (isDone) {
                deadline.toggle();
            }
            return deadline;
        } catch (DateTimeParseException e) {
            System.err.println("Failed to parse deadline date: " + parts[3]);
            return null;
        }
    }

    private static ToDo parseLineToToDo(String description, boolean isDone) {
        ToDo todo = new ToDo(description);
        assert todo != null : "Task object should not be null";
        if (isDone) {
            todo.toggle();
        }
        return todo;
    }
}
