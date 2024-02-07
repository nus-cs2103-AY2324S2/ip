import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String from, String to) throws DateException {
        super(description);
        DateTimeParser dateTimeParserFrom = new DateTimeParser(from);
        DateTimeParser dateTimeParserTo = new DateTimeParser(to);

        this.from = dateTimeParserFrom.getDateTime();
        this.to = dateTimeParserTo.getDateTime();
    }

    @Override
    public String toDataString() {
        return "E|" + super.toDataString() + "|" + this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                + "|" + this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    private String formatDateTime(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formatDateTime(this.from)
                + " to: " + formatDateTime(this.to) + ")";

    }
}
