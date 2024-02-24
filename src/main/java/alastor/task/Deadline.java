package alastor.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructs a Deadline task.
     *
     * @param description The description of the task.
     * @param by The deadline of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        String byString = this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy, hh:mma"));
        return "[D]" + super.toString() + " (by: " + byString + ")";
    }

    @Override
    public String toFile() {
        return "D | " + super.toFile() + " | " + this.by.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deadline) {
            Deadline other = (Deadline) obj;
            if (super.equals(other)) {
                return this.by.equals(other.by);
            }
        }
        return false;
    }
}

