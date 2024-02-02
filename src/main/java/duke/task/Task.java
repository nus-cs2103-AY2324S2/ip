package duke.task;

/**
 * Represents a task with a description and completion status.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a Task object with the given description and default completion status to be not done.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the status icon representing whether or not the task has been completed.
     *
     * @return The status icon ("X" if done, " " if not done).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done by setting the completion status to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done by setting the completion status to false.
     */
    public void unMarkAsDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task in the format "[status icon] description".
     *
     * @return String representation of the task.
     */
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    /**
     * Converts the task into a string format suitable for writing to a file.
     *
     * @return Formatted string for writing to a file.
     */
    public String toFileString() {
        return String.format("duke.task.Task | %d | %s", isDone ? 1 : 0, description);
    }
}

