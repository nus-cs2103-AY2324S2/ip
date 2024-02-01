package pingmebot.task;

/**
 * Represents a task with a task description and a boolean flag to know if the task has been completed.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task object with the task description and a boolean flag initialised.
     *
     * @param description Task's description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a specified task's status icon.
     *
     * @return The specified task's status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as completed by setting the isDone boolean flag to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as uncompleted by setting the isDone boolean flag to false.
     */
    public void uncheckingTask() {
        this.isDone = false;
    }

    /**
     * Returns a string representation to know if a task has been completed or not.
     *
     * @return A string to indicate if task has been completed or not.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns an integer boolean flag of the isDone variable.
     *
     * @return An integer representation to indicate if the task has been completed or not.
     */
    public int hasCompleted() {
        return this.isDone ? 1 : 0;
    }

}
