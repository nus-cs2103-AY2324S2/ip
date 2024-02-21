package pyrite.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Task that contains a deadline.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructs a Deadline.
     *
     * @param description Description of the deadline.
     * @param by          Date and time of the deadline.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }
    /**
     * Generate a string representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm"))
                + ")";
    }
}
