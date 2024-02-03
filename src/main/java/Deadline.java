package task;

import task.Task;

/**
 * Represents a task with a specified deadline.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructs a Deadline object with the given description and deadline.
     *
     * @param description Description of the deadline.
     * @param by Deadline date and time.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline object.
     *
     * @return String representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
