package seiki.data.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime dateTime;

    public Deadline(String taskName, LocalDateTime dateTime) {
        super(taskName);
        this.dateTime = dateTime;
    }

    public Deadline(String taskName, LocalDateTime dateTime, boolean isDone) {
        super(taskName, isDone);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "[D] " + super.taskTitle + " (by: "
                + this.dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm"))
                + ")" + super.getStatusIcon();
    }

    @Override
    public String toFile() {
        return "D " + super.toFile() + " | "
                + this.dateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));
    }
}
