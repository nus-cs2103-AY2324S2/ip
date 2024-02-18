package bmo.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {

    protected LocalDateTime deadline;

    public Deadlines(String task, LocalDateTime deadline) {
        super(task);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return this.deadline.format(DateTimeFormatter.ofPattern("d' 'MMMM' 'yyyy', 'h:mma"));
    }

    @Override
    public String toSaveData() {
        String done = super.getStatus() ? "1" : "0";
        String deadlineStr = this.deadline.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        return "D | " + done + " | " + super.toString() + " | " + deadlineStr + "\n";
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + getDeadline() + ")";
    }

}
