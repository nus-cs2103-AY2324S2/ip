package tasks;

/**
 * The Task class represents a task with a description and a status indicating whether it is done or not.
 * It provides methods to get the task's status icon, mark it as done or not done, and generate string representations.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a Task object with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return A string containing "X" if the task is done, or a space if it is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
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
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string containing the task's status icon and description.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + this.description;
    }

    /**
     * Returns a string representation of the task for saving to file.
     *
     * @return A string containing the task's status (0 for not done, 1 for done) and description in a format suitable for file storage.
     */
    public String toStringForFile() {
        return "| " + (this.isDone ? "1": "0") + " | " + this.description;
    }
}
