import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    @Override
    public String saveTask() {
        return "[E]" + super.toString() + " from: " + this.from + " to: " + this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " +
                this.from.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + " to: " +
                this.to.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }
}
