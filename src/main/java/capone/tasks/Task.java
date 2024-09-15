package capone.tasks;

/**
 * Represents an abstract task.
 * Contains common attributes and methods shared by various types of tasks.
 *
 * @author Tay Rui-Jie
 */
public abstract class Task {
    /**
     * Enumeration representing the type of task
     * (TODO, DEADLINE, EVENT).
     */
    protected enum TaskType {
        TODO("todo"), DEADLINE("deadline"), EVENT("event");

        private String taskName;

        TaskType(String name) {
            this.taskName = name;
        }

        @Override
        public String toString() {
            return this.taskName;
        }
    }

    /** The type of the task */
    protected TaskType taskType;

    /** The description of the task. */
    protected String description;

    /** The completion status of the task */
    protected boolean isDone;

    /**
     * Constructs a task with the specified type, description, and completion status.
     *
     * @param type     The type of the task.
     * @param description The description of the task.
     * @param status   The completion status of the task.
     */
    public Task(TaskType type, String description, boolean status) {
        this.taskType = type;
        this.description = description;
        this.isDone = status;
    }

    /**
     * Gets the type of the task as a string.
     *
     * @return The type of the task.
     */
    public String getTaskType() {
        return this.taskType.toString();
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the description of the task.
     */
    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * Gets the completion status of the task.
     *
     * @return The completion status of the task.
     */
    public Boolean getStatus() {
        return this.isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markTask() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as done.
     */
    public void unmarkTask() {
        this.isDone = false;
    }

    /**
     * Gets the status icon for the task (X if done, empty if not).
     *
     * @return The status icon for the task.
     */
    private String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Overrides the toString method to represent the task as a string.
     *
     * @return A formatted string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
