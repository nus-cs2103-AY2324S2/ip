import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    protected static final LocalTime DEFAULT_TIME = LocalTime.of(0, 0);
    protected static final DateTimeFormatter SAVE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm");

    protected static final DateTimeFormatter DATE_INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy");
    protected static final DateTimeFormatter DATE_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("d MMM yyyy");
    protected static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");
    protected static final String USAGE_HINT = "Usage: event [task description] /from [d/m/yyyy] {hh:mm 24hr format} "
            + "/to [d/m/yyyy] {hh:mm 24hr format}";

    /**
     * Creates new Event with specified description, start, and end time.
     *
     * @throws NollidException if end time is before start time.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) throws NollidException {
        super(description);

        if (from.isAfter(to)) {
            throw new NollidException("Start time and date must be before end time and date.");
        }
        this.from = from;
        this.to = to;
    }

    public LocalDateTime getFrom() {
        return this.from;
    }

    public LocalDateTime getTo() {
        return this.to;
    }

    public String getFromString() {
        return this.from.format(DATE_OUTPUT_FORMAT) + " " + this.from.format(TIME_FORMAT);
    }

    public String getToString() {
        return this.to.format(DATE_OUTPUT_FORMAT) + " " + this.to.format(TIME_FORMAT);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.getFromString() + " to: " + this.getToString() + ")";
    }
}
