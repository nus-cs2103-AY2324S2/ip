package tasks;

/**
 * Represents a task that does not have a deadline.
 */
public class ToDo extends Task {
    /**
     * Creates a Todo object.
     *
     * @param task Indicates what the task is about.
     */
    public ToDo(String task) {
        super(task);
    }

    /**
     * Creates a Todo object.
     *
     * @param task Indicates what the task is about.
     * @param isComplete Indicates whether the task has been completed or not.
     */
    public ToDo(String task, String dateOfReminder, boolean isComplete) {
        super(task, dateOfReminder, isComplete);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String stringify() {
        return "[T]" + super.stringify();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "T | " + super.toString();
    }

}
