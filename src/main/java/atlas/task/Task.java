package atlas.task;

/**
 * Represents an abstract Task in the Atlas application.
 * A Task object corresponds to a task with a description, a done status, and a priority level.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected int priority; // Assuming 1(highest) to 5(lowest)

    /**
     * Constructs a {@code Task} with the given description and priority.
     * The task is initially marked as not done.
     *
     * @param description A string representing the task's description.
     * @param priority    The priority level of the task, where 1 is highest and 5 is lowest.
     */
    public Task(String description, int priority) {
        this.description = description;
        this.isDone = false;
        this.priority = priority;
    }

    /**
     * Returns the status icon of the task, where "X" represents a completed task.
     *
     * @return A string representing the task's completion status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the description of the task.
     *
     * @return A string containing the task's description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a string representation of the task, including its status icon and priority.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s (P:%d)", this.getStatusIcon(), this.description, this.priority);
    }

    /**
     * Toggles the task's done status.
     */
    public void toggle() {
        this.isDone = !this.isDone;
    }

    /**
     * Converts the task into a format suitable for file storage.
     *
     * @return A string formatted for file storage.
     */
    public abstract String toFileFormat();

    /**
     * Sets the priority level of the task.
     *
     * @param priority The priority to set for the task.
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Returns the priority level of the task.
     *
     * @return The task's priority level.
     */
    public int getPriority() {
        return this.priority;
    }
}
