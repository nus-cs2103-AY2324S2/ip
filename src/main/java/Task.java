/**
 * Encapsulates the behaviour of a task.
 *
 * @author Titus Chew
 */
public class Task {
    /**
     * The name of the task.
     */
    private final String name;

    /**
     * Whether the task is completed.
     */
    private boolean isCompleted = false;

    /**
     * Constructor for a task.
     * @param name the name of the task
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * @return the task in a human-readable string
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", isCompleted ? "X" : " ", name);
    }

    /**
     * Mark the task as done.
     */
    public void mark() {
        isCompleted = true;
    }

    /**
     * Change the status of the task back to not done.
     */
    public void unmark() {
        isCompleted = false;
    }
}
