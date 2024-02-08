package duke.task;

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
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("d MMM yyyy| hh:mm a")) +
                " to: " + to.format(DateTimeFormatter.ofPattern("d MMM yyyy| hh:mm a")) + ")";
    }

    @Override
    public String toStringForFile() {
        return "E | " + super.toStringForFile() + " | " + this.from + " | " + this.to;
    }
}
