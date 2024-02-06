import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private String start;
    private String end;

    public Event(String description, LocalDate start, LocalDate end) {
        super(description);
        this.start = start.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        this.end = end.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }
}
