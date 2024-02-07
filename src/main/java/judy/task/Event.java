package judy.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    private final DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }
    private String getDateTimeString(LocalDateTime dateTime) {
        return dateTime.format(pattern);
    }

    @Override
    public String toString() {
        String fromDateTime = getDateTimeString(from);
        String toDateTime = getDateTimeString(to);
        return " [E]" + super.toString() + " (from: " + fromDateTime + " to: " + toDateTime + ")";
    }

    @Override
    public String encode() {
        String fromDateTime = getDateTimeString(from);
        String toDateTime = getDateTimeString(to);
        return " E" + super.encode() + " | " + fromDateTime + " - " + toDateTime;
    }

}
