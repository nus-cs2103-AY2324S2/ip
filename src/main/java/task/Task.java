package task;

/**
 * A task created by the user to store in the task list.
 */
public class Task {
    /**
     * The task description as a string.
     */
    private String description;
    /**
     * A variable that determines whether a task has been completed.
     */
    private boolean isDone;

    /**
     * Task class constructor.
     * @param description The task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Task class constructor.
     * @param description The task description.
     * @param isDone A variable that determines whether a task has been completed.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks whether a task has been done or not.
     * @param isDone isDone A variable that determines whether a task has been completed.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Outputs the task as a string to the user.
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] "
                + this.description;
    }

    /**
     * Outputs the task as a string to be stored in the save file.
     * @return A string representing the task to be saved.
     */
    public String toFileString() {
        return "|" + (this.isDone ? "1" : "0") + "|"
                + this.description;
    }

    /**
     * Checks if task description contains the given keyword.
     * @param keyword The keyword that the user is filtering.
     * @return A boolean value depending on whether the task description contains the keyword.
     */
    public boolean contains(String keyword) {
        return this.description.contains(keyword);
    }
    /**
     * Gets the description of the task.
     * @return The task description.
     */
    public String descriptionToString() {
        return this.description;
    }
}
