package fishstock;

/**
 * Encapsulates a Task object.
 */
abstract class Task {
    private String description;
    private boolean isDone;

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
     * @return The converted int.
     */
    protected int toSaveIsDone() {
        return isDone ? 1 : 0;
    }

    protected String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.description;
    }
}
