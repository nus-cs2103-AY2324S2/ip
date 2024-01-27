package Task;

public class Deadline extends Task {
    private String by;
    private DateTime deadline;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.deadline = new DateTime(by);
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.deadline = new DateTime(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.formatDate() + "hrs)";
    }

    @Override
    public String toFileString() {
        return "D" + super.toFileString() + "|" + this.by;
    }
}
