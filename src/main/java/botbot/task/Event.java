package botbot.task;

import botbot.exception.InvalidDateException;

import java.time.LocalDateTime;

public class Event extends Task {
    String from;
    String to;
    LocalDateTime fromTime;
    LocalDateTime toTime;
    public Event(String task, String from, String to) throws InvalidDateException {
        super(task);
        this.from = from;
        this.to = to;
        this.fromTime = Task.parseDate(from);
        this.toTime = Task.parseDate(to);
    }
    @Override
    public String getRep() {
        return String.format("[E]%s (from: %s to: %s)",
                super.getRep(), this.fromTime.format(Task.TIME_FORMAT_OUT), this.toTime.format(Task.TIME_FORMAT_OUT));
    }
    @Override
    public String fileRep() { return "E|" + super.fileRep() + "|" + from + "|" + to; }

}
