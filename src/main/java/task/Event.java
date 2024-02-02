package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    public LocalDateTime getFrom() {
        return this.from;
    }
    
    public LocalDateTime getTo() {
        return this.to;
    }
    
    @Override
    public String toStorageString() {
        return "E | " + super.toStorageString() + String.format(" | %s | %s", 
                this.getFrom().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), 
                this.getTo().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s to: %s)", 
                this.getFrom().format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")), 
                this.getTo().format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm")));
    }
}
