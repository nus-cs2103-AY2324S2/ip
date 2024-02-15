package duke.task;

/**
 * The Task class represents a generic task with a description and completion status.
 * It serves as the base class for more specific task types.
 */
public class Task {
    /** The description of the task. */
    protected String description;
    /** The type of the task (e.g., ToDo, Deadline, Event). */
    protected TaskType type;
    /** The completion status of the task. */
    protected boolean isDone;
    /** The status icon indicating whether the task is done or not. */
    protected String statusIcon;

    /**
     * Constructs a Task object with the specified type and description.
     *
     * @param type        The type of the task.
     * @param description The description of the task.
     */
    public Task(TaskType type, String description) {
        this.type = type;
        this.description = description;
        this.isDone = false;
        updateStatusIcon();
    }

    /**
     * Constructs a Task object with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Checks and returns the completion status of the task.
     *
     * @return True if the task is marked as done, false otherwise.
     */
    public boolean checkStatus() {
        return this.isDone;
    }

    /**
     * Gets the type of the task.
     *
     * @return The type of the task (e.g., ToDo, Deadline, Event).
     */
    public TaskType getType() {
        return this.type;
    }

    /**
     * Updates the status icon based on the completion status.
     */
    private void updateStatusIcon() {
        this.statusIcon = (isDone ? "X" : " ");
    }

    /**
     * Gets the status icon indicating whether the task is done or not.
     *
     * @return The status icon ("X" if done, " " if not done).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

}
