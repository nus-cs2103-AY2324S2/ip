package demon;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that is an Event.
 * Event class has description, from a certain date and time, to a certain date and time.
 */
public class Event extends Task {

    protected LocalDateTime dateTimeFrom;
    protected LocalDateTime dateTimeTo;
    public Event(String description, LocalDateTime dateTimeFrom, LocalDateTime dateTimeTo) {
        super(description);
        this.dateTimeFrom = dateTimeFrom;
        this.dateTimeTo = dateTimeTo;
    }

    @Override
    public String toString() {
        String description = super.toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("h:mm a");
        String fromDate = dateTimeFrom.format(formatter);
        String fromTime = dateTimeFrom.format(timeFormat);
        String toDate = dateTimeTo.format(formatter);
        String toTime = dateTimeTo.format(timeFormat);
        return "[E]" + description
                + " (from: " + fromDate + " " + fromTime + " to: " + toDate + " " + toTime + ")";
    }

    @Override
    public LocalDateTime getDateTimeFrom() {
        return this.dateTimeFrom;
    }

    @Override
    public LocalDateTime getDateTimeTo() {
        return this.dateTimeTo;
    }
}
