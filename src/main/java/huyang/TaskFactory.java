package huyang;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TaskFactory {
    private static final String DATE_PATTERN = "yyyy-MM-dd HHmm";

    public static Task createTask(String input, Parser.CommandType commandType) throws TaskException {
        switch (commandType) {
        case TODO:
            return createTodoTask(input);
        case DEADLINE:
            return createDeadlineTask(input);
        case EVENT:
            return createEventTask(input);
        default:
            throw new TaskException("Invalid task type.");
        }
    }

    private static ToDo createTodoTask(String input) throws TaskException {
        if (input.strip().length() <= 5) {
            throw TaskException.forEmptyTaskDescription();
        }
        return new ToDo(input.substring(5).trim());
    }

    private static Deadline createDeadlineTask(String input) throws TaskException {
        if (input.strip().length() <= 9) {
            throw TaskException.forEmptyTaskDescription();
        }
        int byIndex = input.indexOf("/by");
        if (byIndex == -1) {
            throw TaskException.forInvalidTaskFormat("deadline");
        }
        try {
            String description = input.substring(9, byIndex).trim();
            String by = input.substring(byIndex + 4).trim();
            LocalDateTime byTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern(DATE_PATTERN));
            return new Deadline(description, byTime);
        } catch (DateTimeParseException e) {
            throw new TaskException("Invalid date format. Please use " + DATE_PATTERN + ", e.g., 2020-12-02 1800.");
        }
    }

    private static Event createEventTask(String input) throws TaskException {
        if (input.strip().length() <= 6) {
            throw TaskException.forEmptyTaskDescription();
        }
        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");
        if (fromIndex == -1 || toIndex == -1) {
            throw TaskException.forInvalidTaskFormat("event");
        }
        try {
            String description = input.substring(6, fromIndex).trim();
            String start = input.substring(fromIndex + 6, toIndex).trim();
            String end = input.substring(toIndex + 4).trim();
            LocalDateTime startTime = LocalDateTime.parse(start, DateTimeFormatter.ofPattern(DATE_PATTERN));
            LocalDateTime endTime = LocalDateTime.parse(end, DateTimeFormatter.ofPattern(DATE_PATTERN));
            return new Event(description, startTime, endTime);
        } catch (DateTimeParseException e) {
            throw new TaskException("Invalid date format. Please use " + DATE_PATTERN + ", e.g., 2020-12-02 1800.");
        }
    }
}