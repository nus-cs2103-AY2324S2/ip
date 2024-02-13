package Duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private final LocalDateTime by;

    /**
     * Constructor for Deadline.
     * @param description Description of the deadline.
     * @param by Date and time of the deadline.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the date and time of the deadline.
     * @return Date and time of the deadline.
     */
    public LocalDateTime getBy() {
        return by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma")) + ")";
    }

    /**
     * Returns the string representation of the deadline for saving to file.
     * @return String representation of the deadline for saving to file.
     */
    @Override
    public String toSaveString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }
}
