import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDateTime from;
    private LocalDateTime to;
    private static final String OUTPUT_PATTERN = "MMM dd yyyy HH:mm";

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getDisplayedString() {
        return "[E]" + getStatusIcon() + " " + getDescription() + " (from: "
                + from.format(DateTimeFormatter.ofPattern(OUTPUT_PATTERN)) + " to: "
                + to.format(DateTimeFormatter.ofPattern(OUTPUT_PATTERN)) + ")";
    }

    @Override
    public String serializeTask() {
        return "[E] | " + getStatusIcon() + " | " + getDescription() + " | " + from + " " + to;
    }

}
