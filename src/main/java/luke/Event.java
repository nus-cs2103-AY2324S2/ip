package luke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a scheduled event with a start and end time.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    protected LocalDateTime fromDate;
    protected boolean hasFromDate;

    protected LocalDateTime toDate;
    protected boolean hasToDate;
    protected DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M/dd/yyyy HHmm");

    protected DateTimeFormatter dateTimeStringFormatter = DateTimeFormatter.ofPattern("dd MMM uuuu hh:mma");

    /**
     * Constructs an Event object with the given description, start date, and end date.
     *
     * @param description the description of the event
     * @param from the start date of the event, in the format "M/dd/yyyy HHmm" (e.g., "2/12/2022 1800")
     * @param to the end date of the event, in the format "M/dd/yyyy HHmm" (e.g., "2/12/2022 2200")
     */
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

    /**
     * Returns a string representation of the event task.
     *
     * @return a string representation of the event task
     */
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

    @Override
    protected String queryType() {
        return "Event";
    }

    @Override
    protected void changeFrom(String newString) {
        this.from = newString;
    }

    @Override
    protected void changeTo(String newString) {
        this.to = newString;
    }
}
