package Duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Event extends Task {
    private String from;
    private String to;

    private LocalDateTime start;
    private LocalDateTime end;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Event(String description, String startString, String endString) {
        super(description);
        this.start = parseDateTime(startString);
        this.end = parseDateTime(endString);
    }

    private LocalDateTime parseDateTime(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString, formatter);
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String getTaskType() {
        return "E"; // Indicates that this task is of type "Event"
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + start.format(formatter) + " - " + end.format(formatter) + ")";
    }

    @Override
    public String toFileString() {
        return String.format("E | %d | %s | %s | %s", isDone ? 1 : 0, description, start.format(formatter), end.format(formatter));
    }
}