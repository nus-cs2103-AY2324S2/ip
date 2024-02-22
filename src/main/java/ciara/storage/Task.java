package ciara.storage;

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
    private boolean isCompleted;

    /**
     * Visibility of the task
     */
    private boolean isArchived;

    /**
     * Constructor for creating a task
     *
     * @param description Description of the task
     * @param type        Type of task
     * @param isCompleted Status of the task
     * @param isArchived  Visibility of the task
     */
    protected Task(String description, TaskType type, boolean isCompleted, boolean isArchived) {
        this.description = description;
        this.type = type;
        this.isCompleted = isCompleted;
        this.isArchived = isArchived;
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
    public boolean getIsCompleted() {
        return this.isCompleted;
    }

    /**
     * Get the visibility of the task
     *
     * @return Visibility of the task
     */
    public boolean getIsArchived() {
        return this.isArchived;
    }

    /**
     * Mark the task as done
     */
    public void mark() {
        this.isCompleted = true;
    }

    /**
     * Mark the task as not done
     */
    public void unmark() {
        this.isCompleted = false;
    }

    /**
     * Archive the task
     */
    public void archive() {
        this.isArchived = true;
    }

    /**
     * Unarchive the task
     */
    public void unarchive() {
        this.isArchived = false;
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
        return String.format("[%s] %s", isCompleted ? "X" : " ", description);
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
                    && this.isCompleted == task.isCompleted;
        }

        return false;
    }
}
