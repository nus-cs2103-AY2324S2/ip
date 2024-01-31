package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime dueDateTime;

    public Deadline(String task, LocalDateTime dueDateTime) {
        super(task);
        this.dueDateTime = dueDateTime;
    }

    @Override
    public String formatTask() {
        String status = getStatus() ? "1" : "0";
        return String.format("D | %s | %s | %s", status, super.formatTask(), dueDateTime);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                dueDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a")));
    }
}
