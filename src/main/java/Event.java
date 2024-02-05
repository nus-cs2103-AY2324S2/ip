import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected String from;
    protected String to;

    protected LocalDateTime fromDate;

    protected LocalDateTime toDate;

    protected boolean hasFromDate;

    protected boolean hasToDate;

    protected DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M/dd/yyyy HHmm");

    protected DateTimeFormatter dateTimeStringFormatter = DateTimeFormatter.ofPattern("dd MMM uuuu ha");
    public Event(String description, String from, String to) {
        super(description);
        try {
            this.fromDate = LocalDateTime.from(this.dateTimeFormatter.parse(from));
            hasFromDate = true;
        } catch (DateTimeParseException e) {
            this.from = from;
            hasFromDate = false;
        }

        try {
            this.toDate = LocalDateTime.from(this.dateTimeFormatter.parse(to));
            hasToDate = true;
        } catch (DateTimeParseException e) {
            this.to = to;
            hasToDate = false;
        }
    }

    @Override
    public String toString() {
        if (hasFromDate && hasToDate) {
            return "[E]" + super.toString() + " (from: "
                    + dateTimeStringFormatter.format(fromDate) + " to: " + dateTimeStringFormatter.format(toDate) + ")";
        } else if (hasFromDate && !hasToDate) {
            return "[E]" + super.toString() + " (from: " + dateTimeStringFormatter.format(fromDate) + " to: " + to + ")";
        } else if (!hasFromDate && hasToDate) {
            return "[E]" + super.toString() + " (from: " + from + " to: " + dateTimeStringFormatter.format(toDate) + ")";
        } else {
            return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
        }

    }
}
