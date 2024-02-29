package jiayou.task;

/**
 * Represents a task in the task list.
 * @author Liu Jiayao
 */
public class Task {
    private String description;
    private boolean isDone = false;

    /**
     * Returns a new task with the given description.
     *
     * @param description the description of the task.
     */
    public Task(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setStatus(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean getStatus() {
        return isDone;
    }

    /**
     * Transforms the task in the task list to a string for storage.
     *
     * @return the string corresponding to the task.
     */
    public String toStringForStore() {
        if (this.isDone) {
            return " | 1 | " + this.description;
        } else {
            return " | 0 | " + this.description;
        }
    }

    /**
     * Transforms the task in the task list to a string for printing in the user interface.
     *
     * @return the string corresponding to the task.
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }

}
