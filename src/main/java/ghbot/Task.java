package ghbot;

/**
 * ghbot.Task class.
 * This class contains informations about the task.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * ghbot.Task Constructor.
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * It is to set isDone to true.
     */
    public void isCompleted() {
        this.isDone = true;
    }

    /**
     * It is to set isDone to false.
     */
    public void isNotCompleted() {
        this.isDone = false;
    }

    /**
     * Return X if isDone is true or " " if isDone is false.
     * @return X or " " depending on isDone.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Return a string description for the item in the list for file operation.
     * @return a string.
     */
    public String toFile() {
        return "| " + (isDone ? "1" : "0") + " | " + this.description.trim();
    }

    /**
     * Return a string description of a task.
     * @return a string that describe the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description.trim();
    }
}
