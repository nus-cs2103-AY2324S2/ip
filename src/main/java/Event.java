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
        String fromString = this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy, hh:mma"));
        String toString = this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy, hh:mma"));
        return "[E]" + super.toString() + "(from:" + fromString + " to:" + toString + ")";
    }
}
