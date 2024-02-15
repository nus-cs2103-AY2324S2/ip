package luke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected String from;
    protected String to;

    protected LocalDateTime fromDate;
    protected boolean hasFromDate;

    protected LocalDateTime toDate;
    protected boolean hasToDate;
    protected DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M/dd/yyyy HHmm");

    protected DateTimeFormatter dateTimeStringFormatter = DateTimeFormatter.ofPattern("dd MMM uuuu hh:mma");

    public Event(String description, String from, String to) {
        super(description);
        try {
            this.fromDate = LocalDateTime.parse(from, dateTimeFormatter);
            this.hasFromDate = true;
        } catch (DateTimeParseException e) {
            this.from = from;
            this.hasFromDate = false;
        }

        try {
            this.toDate = LocalDateTime.parse(to, dateTimeFormatter);
            this.hasToDate = true;
        } catch (DateTimeParseException e) {
            this.to = to;
            this.hasToDate = false;
        }
    }

    @Override
    public String toString() {
        if (hasFromDate && hasToDate) {
            return "[E]" + super.toString() + " (from: " + dateTimeStringFormatter.format(fromDate)
                    + " to: " + dateTimeStringFormatter.format(toDate) + ")";
        } else if (hasFromDate) {
            return "[E]" + super.toString() + " (from: " + dateTimeStringFormatter.format(fromDate)
                    + " to: " + to + ")";
        } else if (hasToDate) {
            return "[E]" + super.toString() + " (from: " + from
                    + " to: " + dateTimeStringFormatter.format(toDate) + ")";
        } else {
            return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
        }
    }
}
