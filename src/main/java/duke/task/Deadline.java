package duke.task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
/**
 * The duke.task.Deadline class represents a task with a specific deadline.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructs a duke.task.Deadline object with the given description and deadline.
     *
     * @param description Description of the task.
     * @param by           duke.task.Deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = parseDateTime(by);
    }

    /**
     * Parses the date and time from the string representation.
     *
     * @param dateTimeString The string representation of date and time.
     * @return The parsed LocalDateTime object.
     */
    private LocalDateTime parseDateTime(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(dateTimeString, formatter);
    }

    /**
     * Gets the deadline of the task.
     *
     * @return The LocalDateTime representing the deadline.
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Returns a formatted string representation of the deadline task".
     *
     * @return The formatted string representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    /**
     * Converts the task into a string format suitable for writing to a file.
     * @return The formatted string for writing to a file.
     */
    @Override
    public String toFileString() {
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, description, by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
    }
}