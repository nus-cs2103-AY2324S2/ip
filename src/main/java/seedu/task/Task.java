package seedu.task;

/**
 * A Task.Task object contains the status of the seedu.task.
 */
public class Task {
    // Whether the seedu.task is completed.
    protected boolean isDone = false;

    /**
     * Constructor for initialising a Task.Task object.
     */
    public Task() { }

    /**
     * Constructor for loading from file.
     *
     * @param b isDone
     */
    public Task(boolean b) {
        this.isDone = b;
    }

    /**
     * Marks the seedu.task done.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Unchecks the seedu.task.
     */
    public void markUndone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return isDone ? "[x]" : "[ ]";
    }
}
