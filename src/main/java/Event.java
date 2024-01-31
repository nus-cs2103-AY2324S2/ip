import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Event extends Task {
    protected String from;
    protected String to;

    protected LocalDate fromDate;
    protected LocalDate toDate;

    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;

        // Check if they are in dateTime format
        // If so, update the string
        try {
            fromDate = LocalDate.parse(from);
            this.from = fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

        } catch (DateTimeParseException e) {}

        try {
            toDate = LocalDate.parse(to);
            this.to = toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {}

    }

    @Override
    public String fileString() {
        return "E | " + super.fileString() + " | " + this.from + " | " + this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
