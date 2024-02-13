package fishstock;

/**
 * Encapsulates a Task object.
 */
abstract class Task {
    private String description;
    private boolean isDone;

    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Mark Task as done.
     */
    protected void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark Task as undone.
     */
    protected void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Converts Task into String format for saving.
     * @return The String format.
     */
    protected abstract String toSaveString();

    /**
     * Converts boolean to int.
     * Used for saving isDone of Task.
     * @return The converted int.
     */
    protected int toSaveIsDone() {
        return isDone ? 1 : 0;
    }

    protected String getDescription() {
        return description;
    }

    protected boolean getIsDone() {
        return isDone;
    }

    /**
     * Makes a clone of the Task.
     * @return The cloned Task.
     */
    protected abstract Task clone();
    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.description;
    }
}
