public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String getDescriptionStatus() {
        return "[D]" + (isDone ? "[X] " : "[ ] ") + this.description + " (by: " + this.by + ")";
    }

    @Override
    public String[] getFields() {
        String[] result = new String[3];
        result[0] = this.description;
        result[1] = this.isDone ? "Y" : "N";
        result[2] = this.by;
        return result;
    }
}
