package duchess.tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Create new Task object.
     *
     * @param description description of Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns status of Task.
     * If status is "done", return "X". Else, return " ".
     *
     * @return String "X" or " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Mark Task object as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Retrieve description of Task.
     *
     * @return String
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Mark Task object as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns String representation of object.
     *
     * @return String.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}