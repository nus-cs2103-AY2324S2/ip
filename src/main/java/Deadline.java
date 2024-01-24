public class Deadline extends Task {
    private String due_by;

    public Deadline(String description, String due_by) {
        super(description);
        this.due_by = due_by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.due_by + ")";
    }
}
