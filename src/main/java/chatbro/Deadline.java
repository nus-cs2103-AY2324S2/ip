package chatbro;

/**
 * Deadline class that represents a chatbro.Task with a deadline.
 */
public class Deadline extends Task {
    protected String by;
    /**
     * Constructor for Deadline class.
     *
     * @param description Description of Deadline object.
     * @param by the deadline of Deadline object.
     */
    public Deadline(String description, String by) {
        super(description);
        type = "D";
        this.by = by;
    }

    /**
     * Overloaded constructor for loading from file, with extra 'isDone' parameter.
     * @param description Description of Deadline object.
     * @param by The deadline of Deadline object.
     * @param isDone Boolean indicating whether the task is done.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        type = "D";
        this.by = by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + Ui.by() + this.by + ")";
    }
    @Override
    public String toStorageFormat() {
        return super.toStorageFormat() + ";;" + this.by;
    }
}
