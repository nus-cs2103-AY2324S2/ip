package TheAdvisor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Represents a deadline task with a description and a deadline.
 * Extends the Task class and implements Serializable for persistence.
 */
public class Deadline extends Task implements Serializable {

    // protected String by;
    protected final LocalDateTime by;

    /**
     * Constructs a new Deadline instance with the given description and deadline.
     *
     * @param description The description of the deadline task.
     * @param by The deadline for the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Gets the type of the task.
     *
     * @return The type of the task, "D " for deadline.
     */
    @Override
    public String getType() {
        return "D ";
    }

    /**
     * Gets the description of the task, including the deadline.
     *
     * @return The description of the task with the deadline.
     */
    @Override
    public String getDescription() {
        return this.description + " | " + this.by;
    }

    /**
     * Gets a string representation of the deadline task.
     *
     * @return A string representation of the deadline task, including type, status, description, and deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(Task.outputFormat) + "hrs)";
    }
}