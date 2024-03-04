package yapper.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import yapper.YapperException;
import yapper.tasks.Deadline;
import yapper.tasks.Event;
import yapper.tasks.Task;
import yapper.tasks.Todo;


/**
 * The Parser class is responsible for parsing task strings and creating Task objects.
 */
public class Parser {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Parses a task string and returns the corresponding Task object.
     *
     * @param taskString Task string to be parsed.
     * @return Task object parsed from the task string.
     * @throws YapperException If parsing encounters an error.
     */
    public static Task parseTask(String taskString) throws YapperException {
        assert taskString != null : "Task string should not be null";

        String[] parts = taskString.split("\\|");
        String taskType = parts[0];
        assert taskType != null : "Task type should not be null";
        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        assert description != null : "Task description should not be null";

        switch (taskType) {
        case "T":
            return new Todo(description, isDone);
        case "D":
            try {
                LocalDateTime by = LocalDateTime.parse(parts[3], DATE_TIME_FORMATTER);
                return new Deadline(description, isDone, by);
            } catch (DateTimeParseException e) {
                throw new YapperException(
                        "Sorry the date format you yapped is invalid :(");
            }
        case "E":
            try {
                LocalDateTime from = LocalDateTime.parse(parts[3], DATE_TIME_FORMATTER);
                LocalDateTime to = LocalDateTime.parse(parts[4], DATE_TIME_FORMATTER);
                return new Event(description, isDone, from, to);
            } catch (DateTimeParseException e) {
                throw new YapperException(
                        "Sorry the date format you yapped is invalid :(");
            }
        default:
            throw new YapperException(
                    "The task type you're yapping about is invalid.");
        }
    }
}
