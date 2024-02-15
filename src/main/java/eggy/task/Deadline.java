package eggy.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {
    /** The deadline of the task. */
    private LocalDateTime by;

    /**
     * Constructs a Deadline.
     *
     * @param name Name of the Deadline.
     * @param by   Deadline of the Deadline.
     */
    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }

    /**
     * Constructs a Deadline.
     *
     * @param name   Name of the Deadline.
     * @param by     Deadline of the Deadline.
     * @param isDone Whether the Deadline is done.
     */
    public Deadline(String name, LocalDateTime by, boolean isDone) {
        super(name, isDone);
        this.by = by;
    }

    /**
     * Returns the string representation of the Deadline.
     *
     * @return String representation of the Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a")) + ")";
    }

    /**
     * Returns the string representation of the Deadline to be saved in a file.
     *
     * @return String representation of the Deadline to be saved in a file.
     */
    @Override
    public String toFileString() {
        return "D | " + super.toFileString() + " | " + this.by;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Deadline) {
            Deadline deadline = (Deadline) obj;
            return this.name.equals(deadline.name) && this.by.equals(deadline.by);
        }
        return false;
    }
}
