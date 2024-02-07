import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime by;
    public Deadline(String description, String by) throws DateException {
        super(description);
        DateTimeParser dateTimeParser = new DateTimeParser(by);

        this.by = dateTimeParser.getDateTime();

    }

    @Override
    public String toDataString() {
        return "D:" + super.toDataString() + ":" + this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    private String formatDateTime(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDateTime(this.by) + ")";
    }

}
