package toothless.task;

/**
 * Abstract class to represent something to be done.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * A public constructor to initialize a new task.
     *
     * @param description A String to describe the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * A public constructor to initialize a task with an isDone value.
     *
     * @param description A String to describe the task.
     * @param isDone A Boolean to describe if the task is done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the done status icon of the task.
     *
     * @return The String indicating X if done, and nothing if not.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Returns a binary of the done status of the task.
     *
     * @return The String indicating 1 if done, and 0 if not.
     */
    public String getStatusBinary() {
        return (this.isDone ? "1" : "0");
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
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Returns a String representing the task formatted for storage in data file.
     *
     * @return String representing the task formatted for storage in data file.
     */
    public String toStorageString() {
        return String.format("%s | %s", this.getStatusBinary(), this.description);
    }

    /**
     * Returns a String representing the task formatted for printing.
     *
     * @return String representing the task formatted for printing.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
