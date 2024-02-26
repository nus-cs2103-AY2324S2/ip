package talktomeorilldie;

/**
 * Represents a task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task.
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
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
     * Returns the status icon of the task.
     * @return Status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Returns the string representation of the task.
     * @return String representation of the task.
     */
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    /**
     * Returns the string representation of the task for saving to file.
     * @return String representation of the task for saving to file.
     */
    public String toSaveString() {
        return "";
    }
}
