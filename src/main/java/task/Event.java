package task;

import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String writeTask() {
        return "E | " + super.writeTask() + " | "
                + from.format(DATE_TIME_STRING_FORMAT) + " | "
                        + to.format(DATE_TIME_STRING_FORMAT);
    }
    
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + from.format(DATE_TIME_STRING_FORMAT)
                        + " to: " + to.format(DATE_TIME_STRING_FORMAT) + ")";
    }
}
