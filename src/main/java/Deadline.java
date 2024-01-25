public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    //Overridden toString method to print type of task, description and time
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }
}
