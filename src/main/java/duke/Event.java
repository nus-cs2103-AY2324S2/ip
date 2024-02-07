package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mma");
        String formattedDateTime = this.start.format(customFormatter);
        String formattedDateTime1 = this.end.format(customFormatter);
        return "[E]" + super.toString() + " (from: " + formattedDateTime + " to: " + formattedDateTime1 + ")";
    }

    @Override
    public String toSave() {
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        String formattedDateTime = this.start.format(customFormatter);
        String formattedDateTime1 = this.end.format(customFormatter);
        return "E | " + (super.isDone ? "1" : "0") + " | " + super.description + " | " + formattedDateTime + " | " + formattedDateTime1;
    }
}