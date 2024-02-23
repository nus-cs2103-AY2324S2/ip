package tiny.tasks;

/**
 * Represents an general task class.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initializes Task.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Initializes Task.
     *
     * @param description Description of the task.
     * @param isDone      Status of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks a task as done.
     */
    public void taskDone() {
        isDone = true;
    }

    /**
     * Unmarks a task as done.
     */
    public void taskUndone() {
        isDone = false;
    }

    /**
     * Returns the status of the task.
     */
    public boolean taskIsDone() {
        return isDone;
    }

    /**
     * Searches the description of the task using the keyword.
     *
     * @param keyword Keyword to search for.
     * @return True if the description contains the keyword, otherwise False.
     */
    public boolean containsDescription(String keyword) {
        return description.contains(keyword);
    }

    /**
     * Formats the task into the correct format to save.
     *
     * @return String of the task in the correct format to save.
     */
    public String formatToSave() {
        return " | " + (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
