package earl.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class representing task of type event.
 */
public class Event extends Task {

    private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter
            .ofPattern("dd/MM/yyyy HHmm");

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Class constructor.
     *
     * @param description  the task description
     * @param from         the date and time of the start
     * @param to           the date and time of the end
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, DATETIME_FORMAT);;
        this.to = LocalDateTime.parse(to, DATETIME_FORMAT);;
    }

    /**
     * Class constructor.
     *
     * @param status       the completion status
     * @param description  the task description
     * @param from         the date and time of the start
     * @param to           the date and time of the end
     */
    public Event(String status, String description, String from, String to) {
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
