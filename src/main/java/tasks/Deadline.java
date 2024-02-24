package tasks;

import java.time.LocalDateTime;

/**
 * represents a task with a specific deadline.
 * adds functionality to store and retrieve the deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructs a Deadline object.
     *
     * @param description The task description.
     * @param by          The deadline for the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Gets the deadline for the task.
     *
     * @return The deadline for the task.
     */
    public LocalDateTime getDueDate() {
        return this.by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by.format(Task.OUTPUT_DATE_FORMAT));
    }
}
