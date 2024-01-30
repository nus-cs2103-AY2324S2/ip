package osiris.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {

    private LocalDate deadline;

    public DeadlineTask(String taskName, LocalDate deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    public DeadlineTask(String taskName, boolean isCompleted, LocalDate deadline) {
        super(taskName, isCompleted);
        this.deadline = deadline;
    }

    public String getDeadlineStr() {
        return this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String getStringStorageRepresentation() {
        return String.format("D | %s | %s", super.getStringStorageRepresentation(), this.getDeadlineStr());
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), this.getDeadlineStr());
    }
}
