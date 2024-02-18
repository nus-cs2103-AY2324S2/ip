package duke;

/**
 * Represents a task with a description and a status indicating whether it is done.
 * This class serves as a base class for various types of tasks, Todo, Event, and Deadline.
 */
public class Task {

    /** The description of the task. */
    protected String description;

    /** The status of the task, where {@code true} indicates the task is done and {@code false} indicates it is not. */

    protected boolean isDone;

    /**
     * Constructs a new Task instance with the specified description. The task is initially marked as not done.
     *
     * @param description The text description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a status icon indicating whether the task is done. "X" represents a completed task,
     * while a space represents an incomplete task.
     *
     * @return A string representing the status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done by setting its status to {@code true}.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done by setting its status to {@code false}.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[" + this.getStatusIcon() + "]" + " " + description);
    }
}
