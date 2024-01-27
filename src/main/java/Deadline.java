public class Deadline extends Task {
    protected String by;
    private static final String type = "D";

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toSaveString() {
        return type + "," + super.toSaveString() + "," + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }
}
