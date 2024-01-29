public class Deadline extends Task {
    private String by;
    public Deadline(String desc, String by) {
        super(desc);
        this.by = by;
    }

    public Deadline(boolean isDone, String desc, String by) {
        super(isDone, desc);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + this.description + " (by: " + this.by + ")";
    }
}
