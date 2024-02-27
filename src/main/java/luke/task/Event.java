package luke.task;

import luke.exception.DateException;
import luke.parser.DateTimeParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task, that is a task with a description and the starting and ending date and time.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructor for Event, which takes in the description and the from and to dates of the event.
     *
     * @param description Description of the event.
     * @param from Starting date and time of the event.
     * @param to Ending date and time of the event.
     * @throws DateException If the date is not in the correct format.
     */
    public Event(String description, String from, String to) throws DateException {
        super(description);
        DateTimeParser dateTimeParserFrom = new DateTimeParser(from);
        DateTimeParser dateTimeParserTo = new DateTimeParser(to);

        this.from = dateTimeParserFrom.getDateTime();
        this.to = dateTimeParserTo.getDateTime();
    }

    /**
     * Returns the string representation of the event task to be saved in the data file.
     *
     * @return The string representation of the event task to be saved in the data file.
     */
    @Override
    public String toDataString() {
        return "E|" + super.toDataString() + "|" + this.from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                + "|" + this.to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Returns the string representation of the event task to be displayed to the user.
     *
     * @return The string representation of the event task to be displayed to the user.
     */
    private String formatDateTime(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formatDateTime(this.from)
                + " to: " + formatDateTime(this.to) + ")";

    }
}
