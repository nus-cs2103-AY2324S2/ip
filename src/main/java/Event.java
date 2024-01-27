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
    public String getTaskType() {
        return "E";
    }
    public LocalDate getFrom() {
        return from;
    }
    public LocalDate getTo() {
        return to;
    }
    @Override
    public String toString() {
        return "[E]" +  super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + " to: "
            + to.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }
}