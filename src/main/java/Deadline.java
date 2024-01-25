public class Deadline extends Task {
    protected String by;

    /**
     * Creates a Deadline task with description and deadline.
     *
     * @param description The description of the Deadline task.
     * @param by The deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
