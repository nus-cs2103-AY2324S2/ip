package eggy.task;

/**
 * Represents a Task.
 */
public abstract class Task {
    /** The name of the task. */
    protected final String name;
    /** Whether the task is done. */
    private boolean isDone;

    /**
     * Constructs a Task.
     *
     * @param name The name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Constructs a Task.
     *
     * @param name   The name of the task.
     * @param isDone Whether the task is done.
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Returns the name of the task.
     *
     * @return The name of the task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the string representation of the Task.
     *
     * @return String representation of the Task.
     */
    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + this.name;
    }

    /**
     * Returns the string representation of the Task to be saved in a file.
     *
     * @return String representation of the Task to be saved in a file.
     */
    public String toFileString() {
        return (isDone ? "1" : "0") + " | " + this.name;
    }
}
