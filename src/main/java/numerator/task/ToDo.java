package numerator.task;

/**
 * Represents a task that is to be done.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo with the specified description
     *
     * @param description should contain information about the task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the task to be saved in storage
     *
     * @return a string to be saved in storage
     */
    @Override
    public String getSaveString() {
        return String.format("T | %d | %s", this.isDone ? 1 : 0, this.description);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.getStatusIcon(), this.description);
    }


}
