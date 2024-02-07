package ellie.task;

/**
 * The abstract class Task serves as the base class for different types of tasks.
 * It contains common properties and methods shared by all task types.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task object with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task object with the given description and completion status.
     *
     * @param description The description of the task.
     * @param isDone      The completion status of the task (1 for done, 0 for not done).
     */
    public Task(String description, int isDone) {
        this.description = description;
        this.isDone = isDone != 0;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The description of the task.
     */
    @Override
    public String toString() {
        return this.description;
    }

    /**
     * Returns the icon representing the completion status of the task.
     *
     * @return "X" if the task is done, " " (empty space) otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the integer representation of the completion status (1 for done, 0 for not done).
     *
     * @return The completion status of the task.
     */
    public int getIsDone() {
        return isDone ? 1 : 0;
    }

    /**
     * Returns a formatted string representation of the task for listing.
     *
     * @return A formatted string representation of the task, including completion status and description.
     */
    public String listTaskString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Marks the task as done.
     */
    public void markTask() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void unmarkTask() {
        this.isDone = false;
    }

    /**
     * Abstract method to be implemented by subclasses.
     * Returns the task type identifier specific to each task type.
     *
     * @return The task type identifier.
     */
    public abstract char getTaskType();

}
