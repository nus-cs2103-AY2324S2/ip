public class Deadline extends Task {
    protected String by;
    static protected String ALIAS = "D";

    public Deadline(String taskName, boolean status, String by) {
        super(taskName, status);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[" + ALIAS + "]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toStore() {
        return ALIAS + super.toStore() + "," + by;
    }
}
