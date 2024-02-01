import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String fromString, String toTimeString) {
        super(description);
        this.from = parseDateTime(fromString);
        this.to = parseDateTime(toTimeString);
    }

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    private LocalDateTime parseDateTime(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        return LocalDateTime.parse(dateTimeString, formatter);
    }

    public String getFormattedFromTime() {
        return from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
    }

    public String getFormattedToTime() {
        return to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getFormattedFromTime()
                        + " to: " + getFormattedToTime() + ")";
    }
}
