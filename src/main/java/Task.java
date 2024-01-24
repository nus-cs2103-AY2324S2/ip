/**
 * Represents a task with a description and a status indicating whether it is done or not.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Create a task with description and the task is marked as not done initially.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
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
     * Gets the current status of the task.
     *
     * @return The status icon of the task followed by its description.
     */
    public String getStatus() {
        String statusSymbol = this.isDone ? "[X]" : "[ ]";
        return statusSymbol + " " + this.description;
    }
}
