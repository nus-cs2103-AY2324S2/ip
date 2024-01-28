package squid.tasks;

public abstract class Task implements TaskInterface {

    /**
     * The name of the task.
     */
    public String taskName;

    protected boolean completed;

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
     *
     * @return The string representation of completeness.
     */
    public String completedIcon() {
        return isCompleted() ? "X" : " ";
    }

    /**
     * Initializer for task.
     * @param taskName The name of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.completed = false;
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
