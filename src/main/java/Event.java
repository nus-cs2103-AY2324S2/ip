import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Event extends Task {
    LocalDate from;
    LocalDate to;
    static final DateTimeFormatter f = DateTimeFormatter.ofPattern("MMM d yyyy");

    public Event(String name, String from, String to) throws InvalidDateFormat {
        super(name.trim());
        try {
            this.from = LocalDate.parse(from.trim());
            this.to = LocalDate.parse(to.trim());
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormat();
        }
    }
    
    @Override
    public String toString() {
        String date = String.format(" (from: %s to: %s)", this.from.format(f), this.to.format(f));
        return "[E]" + super.toString() + date;
    }
}
