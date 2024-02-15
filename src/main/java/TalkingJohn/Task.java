package talkingjohn;

/**
 * Represents a task with a description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unMark() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representing the task, including its completion status and description.
     */
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description; // mark done task with X
    }
}
