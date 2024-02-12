package tyler.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;
    protected static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a");

    public Event(String name, LocalDateTime start, LocalDateTime end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    public Event(String name, LocalDateTime start, LocalDateTime end, boolean isDone) {
        super(name);
        this.start = start;
        this.end = end;
        this.isDone = isDone;
    }

    @Override
    public String saveToFileString() {
        return "E | " + super.saveToFileString() + " | " + this.start.format(OUTPUT_DATE_FORMAT) + " | " + this.end.format(OUTPUT_DATE_FORMAT);
    }

    @Override
    public String toString() {
        String str = super.toString();
        return "[E]" + str + " (from: " + this.start.format(OUTPUT_DATE_FORMAT) + " to: " + this.end.format(OUTPUT_DATE_FORMAT) + ")";
    }
}
