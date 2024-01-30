public class Deadline extends Task {
    private String by;

    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = by;
    }

    public String getDeadline() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " ( by: " + by + ")";
    }
}
