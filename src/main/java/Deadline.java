public class Deadline extends Task {
    private String by;
    Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    @Override
    public String getTypeIcon() {
        return "[D]";
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.getBy() + ")";
    }
}
