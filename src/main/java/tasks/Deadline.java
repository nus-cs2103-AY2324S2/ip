package tasks;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, String status) {
        super(description, status);
        this.by = by;
    }

    @Override
    public String toSaveFormat() {
        return "D " + super.toSaveFormat() + " /by " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
