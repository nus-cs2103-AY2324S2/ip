public class Deadline extends Task {
    protected String by;
    public Deadline (String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline (String description, boolean done, String by) {
        super(description);
        super.updateIsDone(done);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D][" + this.getStatusIcon() + "] " + this.description + " (by: " + this.by + ")";
    }
}
