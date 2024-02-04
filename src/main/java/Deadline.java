public class Deadline extends Task {

    protected String by;
    String DIVIDER = " | ";


    public Deadline(boolean isDone, String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String getFileString() {
        return "D" + DIVIDER + (isDone ? "1" : "0") + DIVIDER + description + DIVIDER + by;
    }

}
