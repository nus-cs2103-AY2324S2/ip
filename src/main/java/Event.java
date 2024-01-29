import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, String from, String to) {
        super(description);
        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            this.from = LocalDate.parse(from, DateTimeFormatter.ofPattern("dd MMM yyyy"));
            this.to = LocalDate.parse(to, DateTimeFormatter.ofPattern("dd MMM yyyy"));
        }
    }

    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            this.from = LocalDate.parse(from, DateTimeFormatter.ofPattern("dd MMM yyyy"));
            this.to = LocalDate.parse(to, DateTimeFormatter.ofPattern("dd MMM yyyy"));
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return "[E]" + super.toString() + " (from: " + from.format(dateFormat) + " to: " + to.format(dateFormat) + ")";
    }
}
