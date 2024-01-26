public class Deadline extends Task {
    private String by;
    public Deadline(String taskName, String deadline) {
        super(taskName, "D");
        this.by = deadline;
        super.setTime(new String[] {deadline, "None"});
    }

    public Deadline(String taskName, String deadline, int isTaskDone) {
        super(taskName, "D");
        this.by = deadline;
        super.changeStatus(isTaskDone);
        super.setTime(new String[] {deadline, "NA"});

    }

    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "] " + super.getTaskName() + " (by: " +
                this.by + ")";
    }
}
