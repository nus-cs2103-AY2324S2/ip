package kai;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a generic tasks.
 * Provides basic functionality for managing tasks, such as marking as done,
 * retrieving status, and obtaining a string representation.
 */
public class Task implements Serializable {

    /**
     * The description of the task.
     */
    private String description;

    /**
     * Indicates whether the task is marked as done.
     */
    private boolean isDone;

    /**
     * The deadline at the task (if applicable).
     */
    private LocalDateTime deadline;

    /**
     * Constructs a Task with the specified description, and default isDone status.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task with the specified description, deadline and default isDone status.
     * @param description The description of the task.
     * @param deadline    The deadline of the task.
     */
    public Task(String description, LocalDateTime deadline) {
        this.description = description;
        this.isDone = false;
        this.deadline = deadline;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns an icon indicating the status of the task.
     *
     * @return The status icon ("[X]" for done, "[]" for not done).
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the deadline of the task.
     *
     * @return The deadline of the task, or null if there is no deadline.
     */
    public LocalDateTime getDeadline() {
        return deadline;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A formatted string representing the task, including status, description, and deadline (if applicable)
     */
    @Override
    public String toString() {
        String status = getStatusIcon();
        String deadlineString = (deadline != null) ? " (by: "
                + deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a")) + ")" : "";
        return status + " " + getDescription() + deadlineString;
    }
}
