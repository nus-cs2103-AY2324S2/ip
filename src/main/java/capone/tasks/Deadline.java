package capone.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String deadlineString;
    private LocalDateTime deadline;

    public Deadline(String description, boolean status, String deadline) {
        super(TaskType.DEADLINE, description, status);
        this.deadlineString = deadline;
    }

    public Deadline(String description, boolean status, LocalDateTime deadline) {
        super(TaskType.DEADLINE, description, status);
        this.deadline = deadline;
    }

    public String getDeadline() {
        if (this.deadline != null) {
            return this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        }
        return this.deadlineString;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDeadline() + ")";
    }
}
