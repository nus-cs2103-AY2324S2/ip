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

    public String fileSavingString() {
        return "D | " + Integer.toString(super.isDone ? 1 : 0) + " | " + super.description + " | " + this.by;
    }
}
