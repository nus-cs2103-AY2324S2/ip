package tasks;

/**
 * Represents a task.
 */
public class Task {
    private String description;
    private Boolean isDone;

    /**
     * Constructs a new Task instance.
     *
     * @param description The description of the task.
     * @param isDone      Indicates whether the task has been completed.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Checks whether task has been completed
     *
     * @return The boolean status of the task.
     */
    public Boolean getDone() {
        return this.isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string with the marked or unmarked done status and description of the task.
     */
    @Override public String toString() {
        if (this.isDone) {
            return "[X] " + this.description;
        }
        return "[ ] " + this.description;
    }

    /**
     * Returns a string representation of the task to save to file.
     *
     * @return A string representing the task for file storage.
     */
    public String toFileString() {
        return (this.isDone ? "1" : "0") + "|" + this.description;
    }
}
