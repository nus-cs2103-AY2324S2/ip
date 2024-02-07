package action.task;

import handler.DateTimeHandler;

import java.time.LocalDateTime;

public class EventTask extends Task{
    private LocalDateTime from;
    public String getFromString() {
        return DateTimeHandler.formatOutput(from);
    }
    private LocalDateTime to;
    public String getToString() {
        return DateTimeHandler.formatOutput(to);
    }
    public EventTask(String taskName, LocalDateTime from, LocalDateTime to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + getFromString() +
                " to: " + getToString() + ")";
    }
}
