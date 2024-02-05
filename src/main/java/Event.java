import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate date_from;
    private LocalDate date_to;

    public Event(String description, String date_from, String date_to) {
        super(description);
        this.date_from = parseDate(date_from);
        this.date_to = parseDate(date_to);
    }

    private static LocalDate parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        return LocalDate.parse(date, formatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.date_from + " to: " + this.date_to + ")";
    }

    @Override
    public String dataString() {
        return String.format("event|%s|%s|%s", super.dataString(), this.date_from, this.date_to);
    }
}
