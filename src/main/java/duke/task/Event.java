package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String name, String from, String to) throws DateTimeParseException {
        super(name);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.from = LocalDateTime.parse(from, formatter);
        this.to = LocalDateTime.parse(to, formatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy HHmm"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy HHmm")) + ")";
    }
}
