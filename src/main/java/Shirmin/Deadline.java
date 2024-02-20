package Shirmin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 * <p>
 * Each Deadline object includes a description and a deadline time.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Initializes a new Deadline object with a description and deadline time.
     * <p>
     * The deadline time is parsed from a string into a LocalDateTime object.
     * If parsing fails, the deadline time is set to null.
     *
     * @param description The description of the deadline task.
     * @param by The deadline time as a string in the format "yyyy-MM-dd HHmm".
     */
    public Deadline(String description, String by) {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            this.by = null;
        }
    }

    /**
     * Returns a string representation of the Deadline task.
     * <p>
     * The format includes the type of task ('[D]'), the description, and the deadline time.
     *
     * @return String representing the Deadline task with its status, description, and deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getBy() + ")";
    }

    /**
     * Returns the deadline time formatted as a string.
     * <p>
     * The format of the returned string is "MMM dd yyyy HH:mm".
     *
     * @return Formatted string of the 'by' LocalDateTime field, or null if 'by' is null.
     */
    public String getBy() {
        return this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }
}