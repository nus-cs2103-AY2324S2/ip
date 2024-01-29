/**
 * Deadline class with atttribute of by which is deadline of a task
 */
public class Deadline extends Task {
     /** Deadline of a task */
    private String by;

    /**
     * Constructor of deadline class.
     *
     * @param description describe the deadline task.
     * @param by deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor of deadline class.
     *
     * @param description describe the deadline task.
     * @param by deadline of the task.
     * @param isDone
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * String representation of deadline task.
     *
     * @return String representation of deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    /**
     * String representation for storage.
     *
     * @return String representation for storage of Deadline task.
     */
    @Override
    public String toStorageString() {
        return CommandType.DELETE.toString() + " " + super.toStorageString() + " " + this.by;
    }
}
