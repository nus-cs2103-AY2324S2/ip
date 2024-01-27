import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, boolean isDone, LocalDate from, LocalDate to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[" + getType() + "]" + "[" + getStatusIcon() + "] " + description + " (from: " + from + " to: " + to + ")";
    }

    @Override
    protected String getType() {
        return "E";
    }

    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }

    protected String getFormattedDateRange() {
        return from.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) +
                " to " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}
