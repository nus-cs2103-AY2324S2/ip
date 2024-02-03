package task;

import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a description and status.
 */
public abstract class Task {
    protected String description;
    protected TaskStatus status;
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    /**
     * Constructs a Task object with the given description.
     * The initial status is set to TaskStatus.UNDONE.
     * The task number is incremented.
     * 
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.status = TaskStatus.UNDONE;
    }

    /**
     * Returns the status of the task.
     * 
     * @return the status of the task
     */
    public TaskStatus getStatus() {
        return status;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.status = TaskStatus.DONE;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        this.status = TaskStatus.UNDONE;
    }

    /**
     * Returns the description of the task.
     * 
     * @return the description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a string representation of the task.
     * 
     * @return a string representation of the task
     */
    public abstract String toString();

    /**
     * Encodes a task.
     */
    public abstract String encode();
}
