package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;
    public Event(String title, LocalDateTime from, LocalDateTime to) {
        super(title);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (on: " + from.format(DateTimeFormatter.ofPattern("dd MMM yyyy h:mma")) + " to " + to.format(DateTimeFormatter.ofPattern("h:mma")) + ")";
    }
}
