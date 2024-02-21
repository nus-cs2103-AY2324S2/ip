package lai.task;

/**
 * Represents a task with a description and a completion status.
 * A task can be marked as done or not done.
 */
public class Task {
    /**
     * The description of the task.
     */
    protected String description;

    /**
     * The completion status of the task. True if the task is done, false otherwise.
     */
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the new task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a new Task with the specified description and completion status.
     *
     * @param description The description of the new task.
     * @param isDone The initial completion status of the task. True if the task is initially done, false otherwise.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Retrieves the description of the task.
     *
     * @return The description of the task as a {@code String}.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a string representation of the task's completion status.
     * Returns "X" if the task is done, or a space character otherwise.
     *
     * @return A string indicating the completion status of the task.
     */
    public String getDone() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void setNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task, including its completion status and description.
     *
     * @return A string in the format "[completion_status] description", where completion_status is "X" if the task is
     * done, or a space character otherwise.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getDone(), this.description);
    }
}