package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    public LocalDateTime from;
    public LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formattedFrom = from.format(outputFormatter);
        String formattedTo = to.format(outputFormatter);
        return String.format("[E]%s (from: %s to: %s)", super.toString(), formattedFrom, formattedTo);
    }
}
