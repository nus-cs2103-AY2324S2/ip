public class Deadline extends Task {
    protected String dateTime;

    public Deadline(String taskName, String dateTime) {
        super(taskName);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "[D] " + super.taskTitle + " (by: " + dateTime + ")" + super.getStatusIcon();
    }
}
