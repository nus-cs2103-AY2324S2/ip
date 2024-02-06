package bytetalker.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String task, LocalDateTime from, LocalDateTime to) {
        super(TaskType.EVENT, task);
        this.from = from;
        this.to = to;
    }

    public Event(String task, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(TaskType.EVENT, task, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[" + getTaskType().getIcon() + "]" + "[" + getStatusIcon() + "] " + getTask() + " (from: " + convertFromToString() + " to:" + convertToToString() + ")";
    }

    public LocalDateTime getFrom() {
        return this.from;
    }

    public LocalDateTime getTo() {
        return this.to;
    }

    public String convertFromToString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma", Locale.ENGLISH);
        String formattedDateTime = this.from.format(outputFormatter);
        return formattedDateTime;
    }

    public String convertToToString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy h:mma", Locale.ENGLISH);
        String formattedDateTime = this.to.format(outputFormatter);
        return formattedDateTime;
    }
}
