package task;

/**
 * Represents a task in the task list.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor for Task.
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for Task.
     * @param description Description of the task.
     * @param isDone Whether the task is done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the description of the task.
     * @return Description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns whether the task is done.
     * @return Whether the task is done.
     */
    public boolean getIsDone() {
        return this.isDone;
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
    public void markNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * @return Status icon of the task.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the string representation of the task.
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
