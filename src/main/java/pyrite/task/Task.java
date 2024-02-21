package pyrite.task;

import java.io.Serializable;

/**
 * Represents a task in the task list.
 */
public class Task implements Serializable {
    /**
     * Enum to represent the status of a task.
     */
    public enum Status {
        DONE,
        NOT_DONE
    }
    protected String description;
    protected Status status;

    /**
     * Constructs a Task.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        assert description != "" : "Description cannot be empty.";
        this.description = description;
        this.status = Status.NOT_DONE;
    }
    /**
     * Returns the status icon of the task.
     *
     * @return Status icon of the task.
     */
    public String getStatusIcon() {
        return (status == Status.DONE ? "X" : " "); // mark done task with X
    }
    /**
     * Returns the description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Generate a string representation of the event.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
    /**
     * Sets the status of the task.
     */
    public void setStatus(Task.Status status) {
        this.status = status;
    }

}
