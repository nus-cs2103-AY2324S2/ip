public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        String status = getStatusIcon();
        return "[D][" + status + "] " + super.toString() + " (by: " + by + ")";
    }
}
