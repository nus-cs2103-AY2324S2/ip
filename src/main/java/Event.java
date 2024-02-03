import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    public Event(String name, LocalDateTime start, LocalDateTime end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    public Event(String name, LocalDateTime start, LocalDateTime end, boolean isDone) {
        super(name, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a")) + " to:" + this.end.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a")) + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + super.toFileString() + " | " + this.start + " | " + this.end;
    }
}
