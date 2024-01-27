public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getTaskType() {
        return "D";
    }
    public String getBy() {
        return by;
    }
    @Override
    public String toString() {
        return "[D]" +  super.toString() + " (by:" + by + ")";
    }
}