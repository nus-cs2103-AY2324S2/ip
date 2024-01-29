import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime fromWhen;
    protected LocalDateTime toWhen;

    public Event(String name, String fromWhen, String toWhen) {
        super(name);
        this.fromWhen = parseTime(fromWhen);
        this.toWhen = parseTime(toWhen);
    }

    private LocalDateTime parseTime(String timeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        return LocalDateTime.parse(timeString, formatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + fromWhen.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"))
                + " to: " + toWhen.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }

    @Override
    public String toWrite() {
        return "E | " + super.toWrite() + " | " + fromWhen.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")) +
                " to " + toWhen.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }
}
