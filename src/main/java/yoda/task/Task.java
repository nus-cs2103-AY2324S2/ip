package yoda.task;

/**
 * Represents a generic task with a description and a completion status.
 * This is an abstract class to be extended by specific types of tasks.
 */
public abstract class Task {

    private final String DESCRIPTION; // Description of the task
    private boolean isDone;           // Flag to track if the task is done

    /**
     * Constructs a Task with the given description. By default, the task is not done.
     *
     * @param description The description of the task.
     */
    protected Task(String description) {
        this.DESCRIPTION = description;
        this.isDone = false; // Task is not done by default
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.DESCRIPTION;
    }

    /**
     * Gets the status of the task.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
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
     * Abstract method to return the type of the task.
     * Subclasses must provide their own implementation.
     *
     * @return The type of the task.
     */
    public abstract String getType();

    /**
     * Returns the status of the task.
     *
     * @return "1" if the task is done, "0" otherwise.
     */
    public String getStatus() {
        return (isDone ? "1" : "0");
    }

    /**
     * Returns a string representation of the event, including its type,
     * @return
     */
    public abstract String toFileFormatDetails();

    /**
     * Returns a string representation of the task, including its done status and description.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + this.getDescription();
    }
}
