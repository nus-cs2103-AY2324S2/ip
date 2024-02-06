package bytetalker.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Deadline extends Task {
    private LocalDateTime deadline;

    public Deadline(String task, LocalDateTime deadline) {
        super(TaskType.DEADLINE, task);
        this.deadline = deadline;
    }

    public Deadline(String task, LocalDateTime deadline, boolean isDone) {
        super(TaskType.DEADLINE, task, isDone);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[" + getTaskType().getIcon() + "]" + "[" + getStatusIcon() + "] " + getTask() + " (by: " + convertDeadlineToString() + ")";
    }

    public String convertDeadlineToString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma", Locale.ENGLISH);
        String formattedDateTime = this.deadline.format(outputFormatter);
        return formattedDateTime;
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }
}
