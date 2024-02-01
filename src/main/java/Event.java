import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime fromDateTime;
    protected LocalDateTime toDateTime;

    public Event(String description, String from, String to) {
        super(description);
        this.fromDateTime = parseDateTime(from);
        this.toDateTime = parseDateTime(to);
    }

    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.fromDateTime = parseDateTime(from);
        this.toDateTime = parseDateTime(to);
    }

    private LocalDateTime parseDateTime(String dateTime) {
        // Assuming that the date/time format is "dd/MM/yyyy HHmm"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return LocalDateTime.parse(dateTime, formatter);
    }

    public LocalDateTime getTo() {
        return this.toDateTime;
    }

    public  LocalDateTime getFrom() {
        return this.fromDateTime;
    }

    @Override
    public String toString() {
        return  "[E]" + super.toString() +
                " (from: " + fromDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) +
                " to: " + toDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + (getIsDone() ? "1" : "0") + " | " + getDescription() + " | " +
                fromDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")) + " | " +
                toDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }
}