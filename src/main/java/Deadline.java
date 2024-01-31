public class Deadline extends Task {
    protected String by;
    protected String taskType = "D";

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    //Overridden toString method to print type of task, description and time
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }

    public String getBy() {
        return this.by;
    }
}
