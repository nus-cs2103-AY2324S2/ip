public class Deadline extends Task{

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toFileString() {
        // Format: D | 0/1 | description | by date
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + getBy();
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
