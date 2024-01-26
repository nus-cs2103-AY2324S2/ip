/**
 * This class represent each task recorded.
 *
 * @author Wong Xu Cheng
 */
public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get the status of the task.
     * X for done, empty space for not done.
     *
     * @return Icon of status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Get the task description
     *
     * @return The task description.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Mark the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmark the task.
     */
    public void unmark() {
        this.isDone = false;
    }

}
