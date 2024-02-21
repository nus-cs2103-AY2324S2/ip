package task;

import java.time.LocalDateTime;

/**
 * Represents a deadline type task.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Creates a Deadline object with description and deadline datetime.
     *
     * @param description Description of task.
     * @param by Deadline of task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public void setBy(LocalDateTime by) {
        this.by = by;
    }

    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public String toWritableString() {
        return "D | " + super.toWritableString() + " | "
                + by.format(DATE_TIME_STRING_FORMAT);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + by.format(DATE_TIME_STRING_FORMAT) + ")";
    }
}
