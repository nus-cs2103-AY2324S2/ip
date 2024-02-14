package tasklist.tasks;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Event Task that needs to be completed. It is an extension of the Task class.
 * Contains 'from' and 'to' date to specify the start and end of the event.
 */
public class Event extends Task {
    // from date as LocalDateTime format.
    protected LocalDateTime fromDateLdt;
    // to date as LocalDateTime format.
    protected LocalDateTime toDateLdt;
    // from date as String format.
    protected String fromDateString;
    // to date as String format.
    protected String toDateString;

    /**
     * Initialize an Event.
     *
     * @param item the event to be completed.
     * @param fromDate start date and time of the event
     * @param toDate end date and time of the event
     */
    public Event(String item, String fromDate, String toDate) {
        super(item);

        try {
            String fromDateString = fromDate.substring(fromDate.indexOf("/from") + 5, fromDate.length());
            String toDateString = toDate.substring(toDate.indexOf("/to") + 3, toDate.length());
            if (fromDateString.trim() == "" | toDateString.trim() == "") {
                throw new EmptyDateException();
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy HHmm");
            LocalDateTime fromDateLdt = LocalDateTime.parse(fromDateString.trim(), formatter);
            LocalDateTime toDateLdt = LocalDateTime.parse(toDateString.trim(), formatter);

            if (fromDateLdt.isAfter(toDateLdt)) {
                throw new IllegalArgumentException("Start date must be before end date");
            }

            this.fromDateLdt = fromDateLdt;
            this.fromDateString = fromDateString.trim();

            this.toDateLdt = toDateLdt;
            this.toDateString = toDateString.trim();

        } catch (DateTimeException dte) {
            throw new DateTimeParseException("Date format is incorrect, please try again", item, 0, dte);
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + fromDateString + " to: " + toDateString + ")";
    }
}
