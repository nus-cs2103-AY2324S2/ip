package squid.tasks;

/**
 * Class encapsulating a Task.
 */
public abstract class Task implements TaskInterface {

    protected boolean completed;

    /**
     * The name of the task.
     */
    private String taskName;

    /**
     * Initializer for task.
     * @param taskName The name of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.completed = false;
    }

    /**
     * Get task name.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Set the task to be either complete or incomplete.
     *
     * @param completed If the task is to be set as complete.
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     * @return Whether the task is complete.
     */
    public boolean isCompleted() {
        return this.completed;
    }

    /**
     * @return The string representation of completeness.
     */
    public String completedIcon() {
        return isCompleted() ? "X" : " ";
    }

    /**
     * @return The type of task.
     */
    public abstract String getType();

    /**
     * @return Additional string representations.
     */
    public abstract String getAdditionalInfo();

    /**
     * @return The string representation of the task to be put into hard disk storage.
     */
    public abstract String parseStr();
}
