public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.getStatus() + " " + super.toString() + " (by: " + this.by + ")";
    }

    @Override
    public String toSaveFormat() {
        return "D | " + (super.isDone ? "1" : "0") + " | " + super.description + " | " + this.by;
    }
}