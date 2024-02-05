package badgpt.tasks;

/**
 * Representation of a task object. The task can have a description and can be completed.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new Task object. It is not done by default.
     *
     * @param description The description of the task (e.g. what must be done).
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status of the task.
     *
     * @return [X] if it is complete. Otherwise, [ ] is returned.
     */
    public String getStatusIcon() {
        return "[" + (isDone ? "X" : " ") + "]"; // mark done task with X
    }

    /**
     * Gets the status of the task.
     */
    public boolean isComplete() {
        return this.isDone;
    }

    /**
     * Changes the status of the task to complete.
     */
    public void complete() {
        this.isDone = true;
    }

    /**
     * Changes the status of the task to incomplete.
     */
    public void uncomplete() {
        this.isDone = false;
    }

    /**
     * Returns a representation of the task to be saved in the text file.
     */
    public String saveTask() {
        return this.toString();
    }

    /**
     * Returns a string representation of the task.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + this.description;
    }
}
