package lrbg.codriver.data;

/**
 * Represents a task.
 */
public abstract class Task {
    /** The description of the task. */
    private final String description;
    /** Whether the task is done. */
    private boolean isDone;

    /**
     * Constructor for Task.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string representation of isDone.
     * @return The string representation of isDone.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
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
     * Returns the description of the task.
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns a string representation of the task to be saved in a file.
     * @return The string representation of the task to be saved.
     */
    public String toFileSaveString() {
        String isDoneString = isDone ? "1" : "0";
        return isDoneString + "|" + this.description;
    }
}
