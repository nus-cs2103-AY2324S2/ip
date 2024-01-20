public class Deadline extends Task {
    private String by;
    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.by = deadline;
    }

    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "] " + super.getTaskName() + " (by: " +
                this.by + ")";
    }
}
