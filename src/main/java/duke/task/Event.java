package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;

    public Event(String task, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        super(task);
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }

    @Override
    public String formatTask() {
        String status = getStatus() ? "1" : "0";
        return String.format("E | %s | %s | %s | %s", status, super.formatTask(), fromDateTime, toDateTime);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                fromDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a")),
                toDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a")));
    }
}
