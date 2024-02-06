import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime by;
    private static final String OUTPUT_PATTERN = "MMM dd yyyy HH:mm";
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getDisplayedString() {
        return "[D]" + getStatusIcon() + " " + getDescription()
                + " (by: " + by.format(DateTimeFormatter.ofPattern(OUTPUT_PATTERN)) + ")";
    }
    @Override
    public String serializeTask() {
        return "[D] | " + getStatusIcon() + " | " + getDescription() + " | " + by;
    }
}
