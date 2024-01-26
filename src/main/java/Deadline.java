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
        this.type = 'D';
        this.by = by;
    }
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
