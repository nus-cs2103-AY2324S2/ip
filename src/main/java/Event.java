import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDateTime start;
    protected LocalDateTime end;
    protected DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    protected DateTimeFormatter stringFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super (description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start.format(stringFormat) + " to: " + end.format(stringFormat) + ")";
    }

    @Override
    public String getData() {
        return "E | " + super.getData() + " | " + start.format(dataFormat) + " | " + end.format(dataFormat);
    }
}
