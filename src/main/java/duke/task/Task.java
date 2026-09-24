package duke.task;

/**
 * The Task class represents a general task with a description and completion status.
 * It is an abstract class that serves as the base class for specific task types such as Todo, Deadline, and Event.
 */
public abstract class Task {
    protected String taskDescription;
    protected boolean isCompleted;

    /**
     * Constructs a Task instance with the specified description and completion status.
     *
     * @param taskDescription The description of the task.
     * @param isCompleted     The completion status of the task.
     */
    public Task(String taskDescription, boolean isCompleted) {
        this.taskDescription = taskDescription;
        this.isCompleted = isCompleted;
    }

    public abstract String getTaskDescription();

    public abstract String getStatusIcon();

    public abstract String getTaskIcon();

    public abstract TaskType getType();

    /**
     * Trims the task description by removing leading and trailing whitespaces.
     *
     * @param taskDescription The original task description.
     * @return The trimmed task description.
     */
    protected String trimDescription(String taskDescription) {
        return taskDescription.trim();
    }

    /**
     * Marks the task as complete.
     */
    public void markAsComplete() {
        this.isCompleted = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void markAsIncomplete() {
        this.isCompleted = false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string containing the task icon, status icon, and task description.
     */
    @Override
    public String toString() {
        return getTaskIcon() + " | "
                + getStatusIcon() + " | " + getTaskDescription();
    }
}
