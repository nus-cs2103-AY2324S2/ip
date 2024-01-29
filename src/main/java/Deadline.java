public class Deadline extends Task {
    String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String type() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D]" + (this.isDone ? "[X] " : "[ ] ") + this.description + " (by: " + this.by + ")";
    }

    public String getBy() {
        return by;
    }
}
