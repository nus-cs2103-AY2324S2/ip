public class Deadline extends Task {
    private String by;
    public Deadline(String desc, int by) {
        super(desc);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + this.description + " (by: " + this.by + " )";
    }
}
