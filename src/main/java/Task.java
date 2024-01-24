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
     * Obtains status icon for task.
     *
     * @return Status icon as a string, if done return "X" if done, else " ".
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Mark task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }
}