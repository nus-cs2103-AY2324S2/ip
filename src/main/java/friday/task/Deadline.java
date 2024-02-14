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
        assert description != null : "Description must not be null";
        assert byString != null : "Deadline string must not be null";
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
        assert description != null : "Description must not be null";
        assert by != null : "Deadline LocalDateTime must not be null";
        this.by = by;
    }
    private LocalDateTime parseDeadline(String byString) {
        assert byString != null : "Deadline string must not be null";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(byString, formatter);
    }

    /**
     * Gets the formatted deadline string ("MMM dd yyyy HHmm").
     *
     * @return The formatted deadline string.
     */
    public String getFormattedDeadline() {
        assert by != null : "Deadline must be initialized";
        return by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
    }

    /**
     * Overrides the toString method to provide a formatted string representation of the Deadline object.
     *
     * @return The formatted string representation of the Deadline object.
     */
    @Override
    public String toString() {
        assert by != null : "Deadline must be initialized";
        return "[D]" + super.toString() + " (by: " + getFormattedDeadline() + ")";
    }
}
