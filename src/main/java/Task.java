/**
 * Represents a user's task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String type;
    protected static int taskCount = 0;

    /**
     * Constructor for Task class.
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for Task class.
     * @param description Description of task.
     * @param isDone Status of task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon of the task.
     * @return Status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X, undone task with whitespace
    }

    /**
     * Returns the description of the task.
     * @return Description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the type of the task.
     * @return Type of the task.
     */
    public String getType() {
        return type;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Increases the task count by 1.
     */
    public static void incrementTaskCount() {
        taskCount++;
    }

    /**
     * Decreases the task count by 1.
     */
    public static void decrementTaskCount() {
        taskCount--;
    }

    /**
     * Returns the task count.
     * @return Task count.
     */
    public static int getTaskCount() {
        return taskCount;
    }

    /**
     * Returns the string representation of the task in storage format.
     * @return String representation of the task in storage format.
     */
    public String toStorageFormat() { // for easy retrieval from file
        return this.getType() + "♢" + this.getStatusIcon() + "♢" + this.getDescription(); // ♢ used as delimiter
    }

    /**
     * Returns the string representation of the task.
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
