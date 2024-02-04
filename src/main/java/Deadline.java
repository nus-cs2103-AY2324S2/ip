public class Deadline extends Task {
    protected String by;

    /**
     * Creates a new Deadline object with the specified description and deadline.
     *
     * @param description
     * @param by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns Deadline task details in the format to be stored in duke.txt.
     *
     * @return Deadline details in file format.
     */
    public String getFileFormat() {
        return "D|" + this.getStatusIcon() + "|" + this.getDescription() + "|"
                + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
