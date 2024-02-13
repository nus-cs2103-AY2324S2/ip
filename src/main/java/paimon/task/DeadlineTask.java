package paimon.task;

import java.time.LocalDateTime;

public class DeadlineTask extends Task {
    private LocalDateTime endDate;

    public DeadlineTask(String description, LocalDateTime endDate) {
        super(description);
        this.endDate = endDate;
    }

    @Override
    public String getTask() {
        if (isDone) {
            return "[D][X] " + this.description + " (by: " + endDate.format(DATE_FORMAT) + ")";
        } else {
            return "[D][ ] " + this.description + " (by: " + endDate.format(DATE_FORMAT) + ")";
        }
    }

    @Override
    public String toFileString() {
        if (this.isDone) {
            return "D | 1 | " + this.description + " | " + this.endDate.format(DATE_FORMAT);
        } else {
            return "D | 0 | " + this.description + " | " + this.endDate.format(DATE_FORMAT);
        }
    }
}
