package fishstock;

/**
 * Encapsulates a Task object.
 */
abstract class Task {
    protected String description;
    protected boolean isDone;

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
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
     * @param bool The boolean to be converted.
     * @return The converted int.
     */
    protected static int boolToInt(boolean bool) {
        return bool ? 1 : 0;
    }

    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.description;
    }
}