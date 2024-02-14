package duke;

/**
 * Represents a generic task.
 * This is an abstract class that contains common properties of
 * a task, such as description and status on whether it is done
 * or not.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initializes a new Task with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
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
     * Gets the status icon of the task, indicating whether it is done or not.
     *
     * @return The status icon ("X" for done, " " for not done).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done and returns a string representation of the task.
     *
     * @return A string representation of the task after marking it as done.
     */
    public String markAsDone() {
        this.isDone = true;
        return this.toString();
    }

    /**
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return A formatted string representing the task.
     */
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.getDescription());
    }

    public abstract String toSave();

    public boolean matchKeyword(String keyword) {
        return this.description.toLowerCase().contains(keyword.toLowerCase());
    }
}
