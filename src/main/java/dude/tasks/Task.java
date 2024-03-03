package dude.tasks;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Task class represents a task with a description and a status.
 */
public class Task implements Serializable {
    private static final String DATE_TIME_FORMAT = "d/M/yyyy H:m";
    private static final String DATE_FORMAT = "d/M/yyyy";
    private final String description;
    private boolean isDone;

    /**
     * Constructor for the Task class.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        assert (description != null);
        this.description = description;
        this.isDone = false;
    }

    /**
     * Mark the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark the task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Get the description of the task.
     *
     * @return The string description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns a string representation of the Task object.
     *
     * @return A string representation of the Task object.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Method to get a LocalDateTime object by parsing a string.
     *
     * @param string The string representation of the date and time to be parsed into a LocalDateTime object.
     *               <p>
     *               String format should be or "d/M/yyyy" or "d/M/yyyy H:m".
     * @return The LocalDateTime object parsed from the string.
     * @throws DateTimeParseException If the date format is invalid.
     */
    protected static LocalDateTime parseDate(String string) throws DateTimeParseException {
        String dateTimePattern = "\\d{1,2}/\\d{1,2}/\\d{4} \\d{1,2}:\\d{2}";
        String datePattern = "\\d{1,2}/\\d{1,2}/\\d{4}";

        if (string.matches(dateTimePattern)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
            return LocalDateTime.parse(string, formatter);
        } else if (string.matches(datePattern)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
            LocalDate date = LocalDate.parse(string, formatter);
            return date.atStartOfDay();
        } else {
            throw new DateTimeParseException("Invalid date format. Use d/M/yyyy or d/M/yyy H:m.", string, 0);
        }
    }

    /**
     * Method to format a LocalDateTime object into a string.
     *
     * @param date The LocalDateTime object to be formatted into a string.
     * @return The string representation of the LocalDateTime object in "MMM d, yyyy @ h:mma" format.
     */
    protected static String formatDate(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d, yyyy @ h:mma"));
    }
}
