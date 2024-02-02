import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String from1 = this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String to1 = this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return "E" + super.toString() + " | from: " + from1 + " to: " + to1;
    }
}
