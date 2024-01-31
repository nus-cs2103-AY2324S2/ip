public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String getTaskType() {
        return "D";
    }
    @Override
    public String toFileString() {
        return String.format("%s | %d | %s | %s", getTaskType(), isDone() ? 1 : 0, getDescription(), by);
    }
}
