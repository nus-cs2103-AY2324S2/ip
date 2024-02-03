package duke.task;

/**
 * An abstract class representing a task.
 */
public abstract class Task {
    private final String description;
    private boolean isDone;

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
     * Constructs a Task with the specified description and completion status.
     *
     * @param description The description of the task.
     * @param isDone      The completion status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return True if the task is completed, false otherwise.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns a status icon for the task.
     *
     * @return "X" if the task is done, " " (a space) if the task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
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
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string containing the task type, status, and description.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    /**
     * Returns a string representation of the task for saving to a file (to be overridden by subclasses).
     *
     * @return A formatted string for saving the task to a file.
     */
    public String toFileString() {
        return "filestring";
    }
}



