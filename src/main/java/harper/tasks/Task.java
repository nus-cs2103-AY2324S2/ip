package harper.tasks;

/**
 * Represents a task with a description and a status indicating whether it is done or not.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Creates a task with description and the task is marked as not done initially.
     *
     * @param description The description of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Gets the current status of the task.
     *
     * @return The status icon of the task followed by its description.
     */
    @Override
    public String toString() {
        String statusSymbol = this.isDone ? "1" : "0";
        return " | " + statusSymbol + " | " + this.description;
    }

    /**
     * Checks whether the task description contains the keyword.
     *
     * @param keyword Keyword to be checked with the task.
     * @return Boolean indicates whether it is a match.
     */
    public boolean matchKeyword(String keyword) {
        return this.description.contains(keyword);
    }

    /**
     * Updates the description of the task.
     *
     * @param description New description.
     */
    public void updateDescription(String description) {
        this.description = description;
    }
}
