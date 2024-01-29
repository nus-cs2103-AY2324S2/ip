import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(String taskName, LocalDateTime start, LocalDateTime end) {
        super(taskName);
        this.start = start;
        this.end = end;
    }

    private String getTypeIcon() {
        return "[E]";
    }

    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        return "E | " + (isDone ? "1" : "0") + " | " + taskName + " | " + start.format(formatter) + " to " + end.format(formatter);
    }

    public static Event fromFileFormat(String fileFormat) throws TaskException {
        String[] parts = fileFormat.split(" \\| ");
        LocalDateTime start = LocalDateTime.parse(parts[3], DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        LocalDateTime end = LocalDateTime.parse(parts[4], DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        Event event = new Event(parts[2], start, end);
        if (parts[1].equals("1")) {
            event.check();
        }
        return event;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return getTypeIcon() + super.getStatusIcon() + " " + taskName +
                " (from: " + start.format(formatter) + " to: " + end.format(formatter) + ")";
    }
}
