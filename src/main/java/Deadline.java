public class Deadline extends Task {
    protected String dateTime;

    public Deadline(String taskName, String dateTime) {
        super(taskName);
        this.dateTime = dateTime;
    }

    public Deadline(String taskName, String dateTime, boolean isDone) {
        super(taskName, isDone);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "[D] " + super.taskTitle + " (by: " + dateTime + ")" + super.getStatusIcon();
    }

    @Override
    public String toFile() {
        return "D " + super.toFile() + " | " + this.dateTime + "\n";
    }
}
