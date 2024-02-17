package youdon;

/**
 * Represents a task in the Youdon task management system.
 * This class provides methods for managing tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new instance of the Task class with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string representing the status icon of the task.
     * The status icon is 'X' if the task is done, and ' ' (space) otherwise.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns a string representation of the task.
     * The string includes the status icon and the description of the task.
     *
     * @return A string representation of the task.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
