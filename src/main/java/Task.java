/**
 * The Task class contains the task with a description and a status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon representing the completion status of the task.
     *
     * @return A string containing "X" if the task is done, or a space if the task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Mark the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Unmark the task.
     */
    public void markAsUnDone() {
        isDone = false;
    }

    /**
     * Returns a string of the Task.
     *
     * @return A string representation of the Task object.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}