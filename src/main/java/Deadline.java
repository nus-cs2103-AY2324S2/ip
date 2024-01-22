public class Deadline extends Action{
    protected String by;

    /**
     * Constructor
     * @param description task to be taken
     * @param by time
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     *
     * @return task output
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + this.by + ")";
    }
}
