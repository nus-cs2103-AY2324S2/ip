public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by.trim();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    @Override
    public String getDetails() {
        return "deadline | " + (this.isDone ? "1 " : "0 |") + this.description
                + " | " + this.by;
    }
}
