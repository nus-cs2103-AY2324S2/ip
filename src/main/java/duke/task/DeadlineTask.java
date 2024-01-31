package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    private LocalDate deadline;

    public DeadlineTask(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    public DeadlineTask(String description, LocalDate deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        LocalDate tempDeadline = deadline.plusDays(0);
        return "[D]" + super.toString() + " (by: " + tempDeadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
