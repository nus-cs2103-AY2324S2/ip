import java.time.LocalDate;
public class Deadline extends Task {
    private LocalDate by;
    public Deadline(String taskName, String deadline) {
        super(taskName, "D");
        this.by = LocalDate.parse(deadline);
        super.setTime(new String[] {deadline, "None"});
    }

    public Deadline(String taskName, String deadline, int isTaskDone) {
        super(taskName, "D");
        this.by = LocalDate.parse(deadline);
        super.changeStatus(isTaskDone);
        super.setTime(new String[] {deadline, "NA"});

    }

    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "] " + super.getTaskName() + " (by: " +
                DateTimeParser.parseDate(this.by) + ")";
    }
}
