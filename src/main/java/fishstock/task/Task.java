package fishstock.task;

/**
 * Encapsulates a Task object.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Mark Task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark Task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Converts Task into String format for saving.
     * @return The String format.
     */
    public abstract String toSaveString();

    /**
     * Converts boolean to int.
     * Used for saving isDone of Task.
     * @return The converted int.
     */
    protected int toSaveIsDone() {
        return isDone ? 1 : 0;
    }

    /**
     * Gets the Task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the done status of the Task.
     */
    protected boolean getIsDone() {
        return isDone;
    }

    /**
     * Makes a clone of the Task.
     * @return The cloned Task.
     */
    public abstract Task clone();

    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.description;
    }
}
