package task;

/**
 * A Task object contains the status of the task.
 */
public class Task {
    protected boolean isDone = false;

    /**
     * Constructor for initialising a Task object.
     */
    public Task() { }

    /**
     * Constructor for loading from file.
     *
     * @param isDone Whether the task is completed.
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

    /**
     * Checks if the task description contains the keyword.
     *
     * @param keyword To be checked in the task description.
     * @return true if contains keyword.
     */
    public boolean isMatchKeyword(String keyword) {
        return false;
    }

    @Override
    public String toString() {
        return isDone ? "[x]" : "[ ]";
    }
}
