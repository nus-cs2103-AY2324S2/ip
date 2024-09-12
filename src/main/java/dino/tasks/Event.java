package dino.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class extends the Task class and represents an event with a start and end time.
 */
public class Event extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;


    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + (isDone() ? "X" : " ") + "] " + getDescription()
                + "(from " + start.format(DateTimeFormatter.ofPattern("HH:mm dd MMM yyyy"))
                + " to "+ end.format(DateTimeFormatter.ofPattern("HH:mm dd MMM yyyy")) + ")";
    }
}
