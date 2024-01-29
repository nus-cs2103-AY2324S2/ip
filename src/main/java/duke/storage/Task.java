package duke.storage;

/**
 * The Task class defined a task used for the application
 *
 * @author Ryan NgWH
 */
public class Task {
    /**
     * Type of task supported by the application
     */
    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    /**
     * Type of task
     */
    private final TaskType type;

    /**
     * Description of the task
     */
    private String description;

    /**
     * Status of the task (Done/not done)
     */
    private boolean isDone;

    /**
     * Constructor for creating a task
     *
     * @param description Description of the task
     * @param type        Type of task
     * @param isDone      Status of the task
     */
    protected Task(String description, TaskType type, boolean isDone) {
        this.description = description;
        this.type = type;
        this.isDone = isDone;
    }

    /**
     * Get the type of the task
     *
     * @return Type of the task
     */
    public TaskType getType() {
        return this.type;
    }

    /**
     * Get the description of the task
     *
     * @return Description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Get the status of the task
     *
     * @return Status of the task
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Mark the task as done
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Mark the task as not done
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Check if task description contains keyword
     *
     * @param keyword Keyword to check against
     *
     * @return True if description contains keyword, false otherwise
     */
    public boolean descriptionContains(String keyword) {
        return this.description.contains(keyword);
    }

    /**
     * String representation of a Task
     *
     * @return String representation of the Task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", description);
    }

    /**
     * Indicates whether some other object is "equal to" this Task
     *
     * @param obj Object to be checked against
     *
     * @return True if equal, False otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof Task) {
            Task task = (Task) obj;

            return this.type.equals(task.type)
                    && this.description.equals(task.description)
                    && this.isDone == task.isDone;
        }

        return false;
    }
}
