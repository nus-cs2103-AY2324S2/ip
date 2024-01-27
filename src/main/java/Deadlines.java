public class Deadlines extends Task {
    protected String by;

    public Deadlines(String description, String by) {
        super(description.replaceFirst("deadline ", ""));
        this.by = by.replaceFirst("by", "");
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }
}