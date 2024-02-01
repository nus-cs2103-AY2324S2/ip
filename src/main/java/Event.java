import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;
    private DateTimeFormatter targetFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + this.from.format(targetFormatter) + " to: " + this.to.format(targetFormatter) + ")";
    }
    @Override
    public String userInputToString() {
        return "[E]" + super.toString() + "(from: " + this.from + " to: " + this.to + ")";
    }
}