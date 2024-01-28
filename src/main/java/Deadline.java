import java.time.LocalDateTime;

public class Deadline extends Task {
    private final static DateTimeParser dateTimeParser = new DateTimeParser();
    private final LocalDateTime deadlineDateTime;
    public Deadline(String description, LocalDateTime deadlineDateTime) {
        super(description);
        this.deadlineDateTime = deadlineDateTime;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + dateTimeParser.formatDateTime(this.deadlineDateTime) + ")";
    }

    @Override
    public String serializeTask() {
        return "D | " + super.serializeTask() + " | " + dateTimeParser.serializeDateTime(this.deadlineDateTime);
    }
}
