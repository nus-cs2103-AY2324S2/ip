package yue.tasks;

/**
 * Represents a deadline task loaded from storage.
 */
public class DeadlineTaskLoad extends Task {
    private String by;

    /**
     * Constructs a DeadlineTaskLoad object with the given task description and deadline.
     *
     * @param task The task description.
     * @param by   The deadline of the task.
     */
    public DeadlineTaskLoad(String task, String by) {
        super(task);
        this.by = by;
    }


    /**
     * Gets the deadline of the task.
     *
     * @return The deadline of the task.
     */
    public String getBy() {
        return by;
    }


    /**
     * Returns a string representation of the deadline task.
     *
     * @return The string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }


    /**
     * Returns the tag for the deadline task.
     *
     * @return The tag for the deadline task.
     */
    @Override
    public String tag() {
        return "[D]";
    }

}

