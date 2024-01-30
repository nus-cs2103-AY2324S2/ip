public class Deadline extends Task {
    protected String by; // date/time to be done before

    public Deadline(String description, boolean completed, String by) {
        super(description, completed);
        this.by = by;
    }

    public String getBy() {
        return this.by;
    }

    public void setBy(String time) {
        this.by = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
