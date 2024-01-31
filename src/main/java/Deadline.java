/**
 * The Deadline class represents a task with a specific deadline.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructs a Deadline object with the given description and deadline.
     *
     * @param description Description of the task.
     * @param by           Deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a formatted string representation of the deadline task".
     *
     * @return The formatted string representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Converts the task into a string format suitable for writing to a file.
     * @return The formatted string for writing to a file.
     */
    @Override
    public String toFileString() {
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, description, by);
    }
}