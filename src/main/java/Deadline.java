public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getDescriptionStatus() {
        return "[D]" + (isDone ? "[X] " : "[ ] ") + this.description + " (by: " + this.by + ")";
    }
}
