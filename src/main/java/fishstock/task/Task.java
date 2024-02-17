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
     * Marks Task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks Task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Converts Task into String format for saving.
     */
    public abstract String toSaveFormat();

    /**
     * Converts boolean mark status to int.
     * Used for saving isDone of Task.
     *
     * @return The converted int.
     */
    protected int markStatusToInt() {
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
     */
    public abstract Task clone();

    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.description;
    }
}
