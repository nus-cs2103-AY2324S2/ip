package task;

/**
 * Encapsulates the behaviour of a task.
 *
 * @author Titus Chew
 */
public abstract class Task {
    /**
     * The name of this task.
     */
    private final String name;

    /**
     * Whether this task is completed.
     */
    private boolean isCompleted = false;

    /**
     * Constructor for this task.
     *
     * @param name the name of this task
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Gets a human-readable description of this task.
     *
     * @return this task in a human-readable string
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", isCompleted ? "X" : " ", name);
    }

    /**
     * Mark this task as done.
     */
    public void mark() {
        isCompleted = true;
    }

    /**
     * Change the status of this task back to not done.
     */
    public void unmark() {
        isCompleted = false;
    }
}
