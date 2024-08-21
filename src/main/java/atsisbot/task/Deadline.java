package atsisbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a atsisbot.task with a deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a Deadline object with the given description and deadline.
     *
     * @param description the description of the atsisbot.task
     * @param by          the deadline of the atsisbot.task
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
        return "[D]["
                + this.getStatus().getStatusIcon()
                + "] "
                + this.getDescription()
                + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"))
                + ") - " + this.priority.toString() + "\n";
    }

    /**
     * Encodes the Deadline task into a string format.
     * The encoded string contains the status, description, and deadline of the task.
     *
     * @return The encoded string representation of the Deadline task.
     */
    @Override
    public String encode() {
        String status = this.getStatus().isDone() ? "1" : "0";
        return "D | "
                + status
                + " | "
                + this.description
                + " | "
                + this.by.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                + " | "
                + this.priority.toString();
    }
}
