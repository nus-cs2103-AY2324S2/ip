package ChatBro;

/**
 * ChatBro.Deadline class that represents a ChatBro.Task with a deadline.
 */
public class Deadline extends Task {
    protected String by;
    /**
     * Constructor for ChatBro.Deadline class.
     *
     * @param description Description of ChatBro.Deadline object.
     * @param by the deadline of ChatBro.Deadline object.
     */
    public Deadline(String description, String by) {
        super(description);
        this.type = "D";
        this.by = by;
    }
    public Deadline(String description, String by, boolean isDone) { // Overloaded constructor: loading from file
        super(description, isDone);
        this.type = "D";
        this.by = by;
    }
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
    @Override
    public String toStorageFormat() {
        return super.toStorageFormat() + "♢" + this.by;
    }
}
