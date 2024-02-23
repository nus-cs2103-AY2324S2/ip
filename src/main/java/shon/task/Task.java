package shon.task;

/**
 * Represents a generic task in the <code>TaskList</code>.
 */
public abstract class Task {
    private String description;
    /** The completion status of the task */
    private boolean isDone;

    /**
     * Creates a new task with the given description and completion status.
     *
     * @param description The description of the task.
     * @param isDone The completion status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the string representation of the completion status of the task.
     * @return "X" if the task is done, " " otherwise.
     */
    private String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Sets the task's completion status to done.
     *
     * @return The string representation of the task.
     * @see Task#toString()
     */
    public String markAsDone() {
        this.isDone = true;
        return this.toString();
    }

    /**
     * Sets the task's completion status to not done.
     *
     * @return The string representation of the task.
     * @see Task#toString()
     */
    public String markAsNotDone() {
        this.isDone = false;
        return this.toString();
    }

    /**
     * Returns the string representation of the task. Completion status marked by X if done.
     *
     * @return Task's completion status and description.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    /**
     * Formats task to be stored. 0 represents not done, 1 represents done.
     *
     * @return Completion status and description, separated by "|".
     */
    protected String formatTask() {
        return (this.isDone ? "1" : "0") + " | " + this.description;
    }

    /**
     * Formats the data of the task to be stored.
     *
     * @return The data of the task formatted to be stored.
     */
    public abstract String formatData();

    /**
     * Checks if task description contains keyword.
     *
     * @param keyword The keyword to look for in task descriptions.
     * @return true if task description contains keyword, false otherwise.
     */
    public boolean hasKeyword(String keyword) {
        return this.description.toLowerCase().contains(keyword.toLowerCase());
    }
}
