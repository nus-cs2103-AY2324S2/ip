package judy.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String fromDateTime = from.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
        String toDateTime = to.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a"));
        return " [E]" + super.toString() + " (from: " + fromDateTime + " to: " + toDateTime + ")";
    }

}
