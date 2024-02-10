package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;

    private DateTimeFormatter printFormatter;
    public Event(String description, LocalDateTime start, LocalDateTime end, DateTimeFormatter formatter) {
        super(description);
        this.start = start;
        this.end = end;
        this.printFormatter = formatter;
    }

    public Event(String description, Boolean status, LocalDateTime start, LocalDateTime end, DateTimeFormatter formatter) {
        super(description, status);
        this.start = start;
        this.end = end;
        this.printFormatter = formatter;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start.format(this.printFormatter)
                + " to: " + this.end.format(this.printFormatter) + ")";
    }

    @Override
    public String convertToFileFormat(DateTimeFormatter storeFormatter) {
        return "E | " + super.convertToFileFormat(storeFormatter) + " | " + this.start.format(storeFormatter)
                + " | " + this.end.format(storeFormatter);
    }
}
