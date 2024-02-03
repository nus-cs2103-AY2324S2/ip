package task;

/**
 * Represents a task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object with the given description.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon based on isDone.
     *
     * @return Status icon of isDone.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Sets the task to be marked as completed.
     */
    public void setMarked() {
        this.isDone = true;
    }

    /**
     * Sets the task to be unmarked as incomplete.
     */
    public void setUnmarked() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the Task object.
     *
     * @return String representation of the task.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}