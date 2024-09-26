package BotChat;

/**
 * Represents a generic task in the botChat application.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        assert description != null && !description.isEmpty() : "Description should not be null or empty";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon indicating whether the task is done.
     *
     * @return The status icon ("X" if done, " " if not done).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Unmarks the task as completed.
     */
    public void unmark() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
