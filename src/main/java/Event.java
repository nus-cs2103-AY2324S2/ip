import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(String event, LocalDateTime start, LocalDateTime end) {
        super(event);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " +
                start.format(DateTimeFormatter.ofPattern("dd MMM yyyy | hh:mm a")) +
                        " to: " + end.format(DateTimeFormatter.ofPattern("dd MMM yyyy | hh:mm a")) + ")";
    }

    @Override
    public String toStringForFile() {
        return "E" + super.toStringForFile() + " | " + this.start + " to " + this.end;
    }

}