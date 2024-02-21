package seedu.mamta;

/**
 * Represents a generic task.
 */
public class Task {

    /**
     * Indicates whether the task is complete.
     */
    protected final boolean isComplete;

    /**
     * The content of the task.
     */
    protected final String content;

    /**
     * Constructs a Task object with the given content.
     * @param content The content of the task.
     */
    Task(String content) {
        this.isComplete = false;
        this.content = content;

    }

    /**
     * Returns the content of the task.
     * @return The content of the task.
     */
    public String getContent() {
        return this.content;
    }

    /**
     * Constructs a Task object with the given completion status and content.
     * @param isComplete The completion status of the task.
     * @param content The content of the task.
     */
    Task(boolean isComplete, String content)
    {
        this.isComplete = isComplete;
        this.content = content;
    }

    /**
     * Marks the task as done.
     * @return A new Task object with the task marked as done.
     */
    public Task markDone() {
        return new Task(true, this.content);
    }

    /**
     * Marks the task as not done.
     * @return A new Task object with the task marked as not done.
     */
    public Task unmarkTask() {
        return new Task(false, this.content);
    }

     public int getStartDate() {
        return 0;
    }

    /**
     * Returns a string representation of the Task object.
     * @return A string representation of the Task object.
     */
    public String toString() {
        if (this.isComplete) {
            return String.format("[X] %s", this.content);
        }
        return String.format("[ ] %s", this.content);
    }
}
