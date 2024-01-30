import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
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
        return "[E]"
                + super.toString()
                + " (from: "
                + start.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm"))
                + " to: "
                + end.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm"))
                + ")";
    }
}


