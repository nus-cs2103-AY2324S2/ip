package fireraya.task;

/**
 * A class for any task.
 *
 * This class acts as a superclass to the Todo, Event and Deadline classes.
 */

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a task.
     * Sets the isDone to false.
     *
     * @param description Name of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Shows the completion icon of a task.
     *
     * @return string of the completion icon of the task.
     */
    public String getCompletionIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    /**
     * Marks a task as complete.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks a task as incomplete.
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Gets the description of current task.
     *
     * @return String of description of task.
     */
    public String getTask() {
        return description;
    }

    /**
     * Gets the format to save the file on local device.
     *
     * @return string of the saved format of the task.
     */
    public String saveFormat() { return String.format("X|%d|%s", isDone ? 1 : 0, description);}


    /**
     * Returns a string with information about this task.
     *
     * @return string format of this task.
     */
    @Override
    public String toString() {
        return this.getCompletionIcon() + this.getTask();
    }

}
