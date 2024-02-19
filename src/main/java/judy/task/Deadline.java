package judy.task;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class represents a task with a specific deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a Deadline object with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param by          The deadline of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Gets a formatted string representation of the deadline.
     *
     * @return The formatted string representation of the deadline.
     */
    private String getDateTimeString() {
        return this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return The formatted string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return " [D]" + super.toString() + " (by: " + getDateTimeString() + ")";
    }

    /**
     * Encodes the Deadline task as a string for storage.
     *
     * @return The encoded string representation of the Deadline task.
     */
    @Override
    public String encode() {
        return " D" + super.encode() + " | " + getDateTimeString();
    }
}