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
import java.util.ArrayList;


public class Parser {
    public static Command parse(String input, TaskList tasks, Ui ui, Storage storage) throws AtlasException {
        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String details = parts.length > 1 ? parts[1] : null;

        switch (command) {
        case "bye":
            return new ExitCommand(tasks, ui, storage);
        case "list":
            return new ListCommand(tasks, ui, storage);
        case "mark":
            int taskIndex = Integer.parseInt(details) - 1;
            return new MarkCommand(tasks, ui, storage, taskIndex);
        case "unmark":
            taskIndex = Integer.parseInt(details) - 1;
            return new UnmarkCommand(tasks, ui, storage, taskIndex);
        case "delete":
            taskIndex = Integer.parseInt(details) - 1;
            return new DeleteCommand(tasks, ui, storage, taskIndex);
        case "todo":
            return new AddToDoCommand(tasks, ui, storage, details);
        case "deadline":
            String[] deadlineDetails = details.split(" /by ");
            LocalDateTime by;
            try {
                by = LocalDateTime.parse(deadlineDetails[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            } catch (DateTimeParseException e) {
                throw new InvalidDeadlineFormatException("Invalid date/time format. Please use 'yyyy-MM-dd HHmm'.\"");
            }
            return new AddDeadlineCommand(tasks, ui, storage, deadlineDetails[0], by);
        case "event":
            String[] eventParts = details.split(" /from | /to "); // Split by both "/from" and "/to"
            if (eventParts.length != 3) {
                throw new AtlasException("Invalid event format. Please use 'event [description] /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm'.");
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
        case "tasks_on":
            LocalDate date;
            try {
                date = LocalDate.parse(details, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (DateTimeParseException e) {
                throw new AtlasException("Invalid date format. Please use 'yyyy-MM-dd HHmm'.");
            }
            return new TasksOnDateCommand(tasks, ui, storage, date);
        case "find":
            return new FindCommand(tasks, ui, storage, details);
        default:
            return new InvalidCommand(tasks, ui, storage);
        }
    }

    public static Task parseLineToTask(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        switch (type) {
        case "T":
            ToDo todo = new ToDo(description);
            if (isDone) todo.toggle();
            return todo;
        case "D":
            try {
                LocalDateTime by = LocalDateTime.parse(parts[3].trim());
                Deadline deadline = new Deadline(description, by);
                if (isDone) deadline.toggle();
                return deadline;
            } catch (DateTimeParseException e) {
                System.err.println("Failed to parse deadline date: " + parts[3]);
                return null;
            }
        case "E":
            try {
                LocalDateTime start = LocalDateTime.parse(parts[3].trim());
                LocalDateTime end = LocalDateTime.parse(parts[4].trim());
                Event event = new Event(description, start, end);
                if (isDone) event.toggle();
                return event;
            } catch (DateTimeParseException e) {
                System.err.println("Failed to parse event dates: " + parts[3] + " and " + parts[4]);
                return null;
            }
        default:
            return null;
        }
    }
}
