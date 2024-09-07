package ghbot.task;

/**
 * Task class.
 * This class contains information about the task.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Task Constructor.
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets isDone to true.
     */
    public void isCompleted() {
        this.isDone = true;
    }

    /**
     * Sets isDone to false.
     */
    public void isNotCompleted() {
        this.isDone = false;
    }

    /**
     * Returns X if isDone is true or " " if isDone is false.
     * @return X Returns X or " " depending on isDone.
     */
    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    /**
     * Returns a string description for the item in the list for file operation.
     * @return A string that describes the item in the list for file operation.
     */
    public String toFile() {
        return "| " + (this.isDone ? "1" : "0") + " | " + this.description.trim();
    }

    /**
     * Returns a string description of a task.
     * @return A string that describes the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description.trim();
    }
}
