import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter
            .ofPattern("dd/MM/yyyy HHmm");

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String status, String description,
                 LocalDateTime from, LocalDateTime to) {
        super(status, description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                from.format(DATETIME_FORMAT), to.format(DATETIME_FORMAT));
    }

    @Override
    public String toStorageString() {
        return String.format("E,%s,%s,%s,%s",
                super.getStatusIcon(), description,
                from.format(DATETIME_FORMAT), to.format(DATETIME_FORMAT));
    }
}
