package duke;

/**
 * Represents a generic task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Checks whether the task is marked as done.
     *
     * @return True if the task is done, False otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Obtains status icon for task.
     *
     * @return Status icon as a string, if done return "X" if done, else " ".
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Marks task as done.
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
     * Returns a string representation of the task for storing in a file.
     * The format of the string depends on the specific task type.
     *
     * @return A string representation of the task in a file-compatible format.
     */
    public String toFileString() {
        return "";
    }
}