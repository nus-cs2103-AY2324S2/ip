/**
 * Represents a generic task with a description and a completion status.
 */
public class Task {

    private final String description; // Description of the task
    private boolean isDone;     // Flag to track if the task is done

    /**
     * Constructs a Task with the given description. By default, the task is not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false; // Task is not done by default
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return this.isDone;
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
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task, including its done status and description.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + this.getDescription();
    }
}
