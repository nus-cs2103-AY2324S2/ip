package task;

/**
 * Tasks that can be completed.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Enum of the different types of Task available.
     */
    public enum ID {
        TODO,
        DEADLINE,
        EVENT
    }

    /**
     * Initialises a task with a description.
     * Is marked as not completed by default.
     *
     * @param description Description of the task to be completed.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Initialises a task with the task completion status and description.
     * Use to manually set the completion status of the task when initialising.
     *
     * @param isDone Completion status of the task.
     * @param description Description of the task to be completed.
     */
    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    public boolean contains(String string) {
        return description.contains(string);
    }

    /**
     * Returns the completion status as an icon.
     *
     * @return Completion status where X is completed and whitespace as not completed.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public int getIsDoneInt() {
        return (isDone) ? 1 : 0;
    }

    @Override
    public abstract String toString();
}
