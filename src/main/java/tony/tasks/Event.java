package tony.tasks;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.type = TaskType.EVENT;
        this.from = from;
        this.to = to;

    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy hh mm")) + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy hh mm")) + ")";
    }

    @Override
    public String formattedString() {
        return "E" + super.formattedString() + "|" + from.toString() + "|" + to.toString();
    }

    @Override
    public String getType() {
        return type.toString();
    }
}
