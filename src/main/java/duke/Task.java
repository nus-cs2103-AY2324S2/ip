package duke;

/**
 * Abstract class Task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a Task.
     * Task is marked as not done by default.
     *
     * @param description description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * If task is done, return "X", else return " ".
     *
     * @return the string representing the status icon
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Mark task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Mark task as not done.
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Format of task saved to text file.
     *
     * @return row of text to be saved to text file
     */
    public String saveToText() {
        return "";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}