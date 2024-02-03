package duke.task;

/**
 * Represents a generic task in the Duke application.
 */

public class Task {

    /**
     * The description of the task.
     */
    protected String description;

    /**
     * A flag indicating whether the task is marked as done.
     */
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
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Checks if the task is marked as done.
     *
     * @return True if the task is marked as done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Gets the type code representing the task type.
     *
     * @return A string representing the task type code.
     */
    public String getType() {
        return "D";
    }

    /**
     * Gets the status icon representing the task's done status.
     *
     * @return A string representing the status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : "O"); // mark done task with X
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
     * Converts the task to a string representation.
     *
     * @return A formatted string representing the task.
     */
    public String toString() {
        return " [" + this.getStatusIcon() + "] " + this.description;
    }
}



