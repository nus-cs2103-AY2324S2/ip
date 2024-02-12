package task;

/**
 * Represents a task with a specified deadline.
 */
public class Deadline extends Task {
    private static final String TASK_CODE = "D";
    protected String by;

    /**
     * Constructs a Deadline object with the given description and deadline.
     *
     * @param description Description of the deadline.
     * @param by Deadline date and time.
     */
    public Deadline(String description, String by) {
        super(TASK_CODE, description);
        this.by = by;
    }

    /**
     * Returns a string representation of the due date of the Deadline object.
     *
     * @return String representation of the due date.
     */
    @Override
    public String getDate() {
        return this.by;
    }

    /**
     * Returns a string representation of the Deadline object.
     *
     * @return String representation of the deadline.
     */
    @Override
    public String toString() {
        return "[" + TASK_CODE + "]" + super.toString() + " (by: " + by + ")";
    }
}
