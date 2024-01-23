public class Deadline extends Task {
    protected String by;

    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = by;
    }

    private String getTypeIcon() {
        return "[D]";
    }

    @Override
    public String toString() {
        return getTypeIcon() + super.getStatusIcon() + " " + taskName + " (by: " + by + ")";
    }
}
