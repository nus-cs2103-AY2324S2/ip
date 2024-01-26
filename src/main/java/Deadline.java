public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String completed, String description, String by) {
        super(description, completed.equals("1"));
        this.by = by;
    }

    @Override
    public String textFormattedOutput() {
        int intIsDone = isDone ? 1 : 0;
        return String.format("D | %d | %s | %s", intIsDone, description, by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}