package friday.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline in the Friday application.
 * Subclass of the Task class.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a Deadline object with the specified description and deadline in string format.
     *
     * @param description The description of the deadline task.
     * @param byString    The deadline in string format ("yyyy-MM-dd HHmm").
     */
    public Deadline(String description, String byString) {
        super(description);
        this.by = parseDeadline(byString);
    }

    /**
     * Constructs a Deadline object with the specified description and deadline in LocalDateTime format.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline in LocalDateTime format.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }
    private LocalDateTime parseDeadline(String byString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(byString, formatter);
    }

    /**
     * Gets the formatted deadline string ("MMM dd yyyy HHmm").
     *
     * @return The formatted deadline string.
     */
    public String getFormattedDeadline() {
        return by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
    }

    /**
     * Overrides the toString method to provide a formatted string representation of the Deadline object.
     *
     * @return The formatted string representation of the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getFormattedDeadline() + ")";
    }
}
