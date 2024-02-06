package earl.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class representing task of type event.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter
            .ofPattern("dd/MM/yyyy HHmm");

    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, DATETIME_FORMAT);;
        this.to = LocalDateTime.parse(to, DATETIME_FORMAT);;
    }

    public Event(String status, String description,
                 String from, String to) {
        super(status, description);
        this.from = LocalDateTime.parse(from, DATETIME_FORMAT);;
        this.to = LocalDateTime.parse(to, DATETIME_FORMAT);;
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
