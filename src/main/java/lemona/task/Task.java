package lemona.task;

/**
 * Represents a generic task in the task manager application.
 * A task has a description and can be marked as done or undone.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon of the task.
     * Returns "X" if the task is done, otherwise returns a space.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the detailed information about the task.
     * This method is intended to be overridden by subclasses.
     *
     * @return The detailed information about the task.
     */
    public String getTaskInfo() {
        return this.description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Prints the formatted string representation of the task.
     *
     * @return The formatted string representation of the task.
     */
    public String print() {
        String str = "[" + this.getStatusIcon() + "] " + this.description;
        return str;
    }
}
