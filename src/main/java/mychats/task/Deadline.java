package mychats.task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructs a Deadline object with the given description and deadline.
     *
     * @param description Description of the task.
     * @param by Deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = parseDateTime(by);
    }

    /**
     * Parses the date and time from the given string that is of "yyyy-MM-dd HHmm" format.
     *
     * @param dateTimeString The string representation of the date and time in
     * "yyyy-MM-dd HHmm" format.
     * @return The parsed LocalDateTime object.
     */
    private LocalDateTime parseDateTime(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(dateTimeString, formatter);
    }

    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Returns a formatted string representation of the deadline task.
     *
     * @return Formatted string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    /**
     * Converts the task into a string format suitable for writing to a file.
     *
     * @return Formatted string for writing to a file.
     */
    @Override
    public String toFileString() {
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, description,
                by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
    }
}