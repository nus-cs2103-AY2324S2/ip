package yapper.command;
import yapper.tasks.*;
import yapper.YapperException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Parser class is responsible for parsing task strings and creating Task objects.
 */
public class Parser {
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Parses a task string and returns the corresponding Task object.
     *
     * @param taskString Task string to be parsed.
     * @return Task object parsed from the task string.
     * @throws YapperException If parsing encounters an error.
     */
    public static Task parseTask(String taskString) throws YapperException {

        String[] parts = taskString.split("\\|");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

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
            try{
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
