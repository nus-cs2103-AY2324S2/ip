import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime start;
    protected LocalDateTime end;
    protected DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d 'of' MMMM yyyy, ha");
    protected static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HHmm");

    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }
    @Override
    public String parseToLogRepresentation() {
        int completionStatus = this.isDone ? 1 : 0;
        return "E|" + completionStatus + "|" + this.description + "|" + this.start.format(outputFormatter) + "|" + this.end.format(formatter);
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start.format(outputFormatter) + " to: " + this.end.format(outputFormatter) + ")";
    }
}