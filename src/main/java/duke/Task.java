package duke;

/**
 * Represents a Task with a description and a completion status
 */
public class Task {
    private String description;
    private boolean isComplete;

    /**
     * Constructs a new Task with the given description
     *
     * @param description The description of the task
     */
    public Task(String description) {
        assert description != null : "Task description should not be null";
        this.description = description;
        this.isComplete = false;
    }

    /**
     * @return The description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as done
     */
    public void markAsDone() {
        this.isComplete = true;
        assert isComplete : "Task should be marked as done";
    }

    /**
     * Unmarks the task as done
     */
    public void markAsUndone() {
        this.isComplete = false;
        assert !isComplete : "Task should be marked as undone";
    }

    /**
     * @return True if the task is done, false otherwise
     */
    public boolean isDone() {
        return isComplete;
    }

    /**
     * @return A string representation of the task
     */
    @Override
    public String toString() {
        return " | " + (isComplete ? "X" : " ") + " | " + description;
    }
}