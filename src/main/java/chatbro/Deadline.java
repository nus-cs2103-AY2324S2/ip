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
    public Deadline(String description, String by, boolean isDone) { // Overloaded constructor: loading from file
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
