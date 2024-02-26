package destiny;

/**
 * Base Task class which contains the base information and functions all tasks share.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task with the given description.
     *
     * @param description A string containing the basic information about the task.
     */
    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    /**
     * Used to return a string that represents if the task has been marked.
     *
     * @return "X" if the task is done, else " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Used to return a string that represents if the task has been marked.
     *
     * @return "1" if the task is done, else "0".
     */
    public String getStatusAsNum() {
        return (isDone ? "1" : "0"); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        this.isDone = true;

        // the task should reflect that it has been marked
        assert this.toString().substring(3, 6).equals("[X]");
    }

    /**
     * Marks this task as not done.
     */
    public void markAsUndone() {
        this.isDone = false;

        // the task should reflect that it has been unmarked
        assert this.toString().substring(3, 6).equals("[ ]");
    }

    /**
     * Checks if given task is equal to current task.
     *
     * @param task The given task.
     * @return True if the current task and given task are the same.
     */
    public abstract boolean equals(Task task);
}
