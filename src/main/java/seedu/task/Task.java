package seedu.task;

/**
 * A Task.Task object contains the status of the task.
 */
public class Task {
    // Whether the task is completed.
    protected boolean isDone = false;

    /**
     * Constructor for initialising a Task.Task object.
     */
    public Task() { }

    /**
     * Constructor for loading from file.
     *
     * @param isDone isDone
     */
    public Task(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Marks the task done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Unchecks the task.
     */
    public void markUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return isDone ? "[x]" : "[ ]";
    }
}
