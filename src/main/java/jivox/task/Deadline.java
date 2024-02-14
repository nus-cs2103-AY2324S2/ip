package jivox.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline represents a task with a deadline.
 * It extends Task.
 */
public class Deadline extends Task {

    private LocalDateTime deadline;

    /**
     * Creates a new Deadline with the given description and deadline.
     *
     * @param content The task description.
     * @param deadline The deadline for the task.
     */
    public Deadline(String content, LocalDateTime deadline) {
        super(content);
        this.deadline = deadline;
    }


    /**
     * Gets the type identifier for Deadline, which is "D".
     *
     * @return The type identifier.
     */
    public String getType() {
        return "D";
    }

    @Override
    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    /**
     * Gets the save format for this Deadline.
     *
     * @return The save string.
     */
    @Override
    public String saveFormat() {
        return this.getType() + " | " + (this.getStatus() ? "1" : "0")
            + " | " + this.getDescription()
                + " | " + this.deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"))
                + ")";
    }
}
