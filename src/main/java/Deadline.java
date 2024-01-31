public class Deadline extends Task {

    protected String by;

    /**
     *
     * @param description
     * @param by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
