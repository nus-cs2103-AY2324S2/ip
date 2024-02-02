public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by.split("by ")[0];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toStringForFile() {
        return "D | " + super.toStringForFile() + " | " + this.by;
    }
}