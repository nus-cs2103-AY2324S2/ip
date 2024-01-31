public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by, boolean isComplete) { // Adjusted constructor
        super(description);
        this.by = by;
        this.isComplete = isComplete;
    }

    @Override
    public String toFileFormat() {
        return String.format("D | %d | %s | %s", isComplete ? 1 : 0, description, by);
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + description + " (by: " + by + ")";
    }

}
