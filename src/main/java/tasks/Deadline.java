package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline, inherits from Task.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Deadline constructor
     */
    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }

    /**
     * Returns a formatted date string.
     */
    public String formatBy() {
        return this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatBy() + ")";
    }

    @Override
    public String fileString() {
        return "D " + super.fileString() + " | " + this.by;
    }

    public LocalDateTime getBy() {
        return by;
    }
}
