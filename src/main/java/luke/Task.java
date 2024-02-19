package luke;

/**
 * Represents a task in the task list.
 * <p>
 * This abstract class defines common attributes and methods for all types of tasks.
 * </p>
 */
public abstract class Task {
    /**
     * Indicates whether the task is done or not.
     */
    protected boolean done;

    /**
     * The description of the task.
     */
    protected String description;

    /**
     * Constructs a new Task object with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.done = false;
        this.description = description;
    }

    /**
     * Returns a string representation of the task.
     * <p>
     * If the task is done, it returns a string with '[X]' prefix, otherwise '[ ]'.
     * </p>
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        if (done) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }

    /**
     * Marks the task as done.
     */
    public void setToDone() {
        this.done = true;
    }

    /**
     * Marks the task as not done.
     */
    public void setToNotDone() {
        this.done = false;
    }

    /**
     * Returns the description of the task.
     * @return description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the most recently added task.
     *
     * @return the most recent task
     */
    protected abstract String queryType();

    /**
     * Changes the description of the task.
     *
     * @param newString The new description for the task.
     */
    protected void changeDescription(String newString) {
        this.description = newString;
    }

    /**
     * Abstract method to change the 'from' attribute of the task.
     * <p>
     * This method should be overridden by subclasses to implement specific behavior.
     * </p>
     *
     * @param newString The new value for the 'from' attribute.
     */
    protected void changeFrom(String newString) {
        assert this.getClass() != Task.class : "changeFrom method should be overridden by subclass";
    };

    /**
     * Method to change the 'to' attribute of the task.
     * <p>
     * This method should be overridden by subclasses to implement specific behavior.
     * </p>
     *
     * @param newString The new value for the 'to' attribute.
     */
    protected void changeTo(String newString) {
        assert this.getClass() != Task.class : "changeFrom method should be overridden by subclass";
    };

    /**
     * Method to change the 'by' attribute of the task.
     * <p>
     * This method should be overridden by subclasses to implement specific behavior.
     * </p>
     *
     * @param newString The new value for the 'by' attribute.
     */
    protected void changeBy(String newString) {
        assert this.getClass() != Task.class : "changeFrom method should be overridden by subclass";
    };
}
