package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a Deadline object with the given description and deadline.
     *
     * @param description the description of the task
     * @param by          the deadline of the task
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline object.
     *
     * @return a string representation of the Deadline object
     */
    @Override
    public String toString() {
        return "[D][" + this.getStatus().getStatusIcon() + "] " + this.getDescription() + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")) + ")\n";
    }

    @Override
    public String encode() {
        String status = this.getStatus().isDone() ? "1" : "0";
        return "D | " + status + " | " + this.description + " | "
                + this.by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
}