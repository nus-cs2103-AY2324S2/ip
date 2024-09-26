package BotChat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start and end date/time in the botChat application.
 * Extends the base Task class.
 */
public class Event extends Task {
    private static final DateTimeFormatter STANDARD_DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final DateTimeFormatter INPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter INPUT_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    protected String from;
    protected String to;

    /**
     * Constructs an Event object with the specified description, start date/time, and end date/time.
     * If the provided dates are in a valid format, they are used as-is; otherwise, they are converted.
     *
     * @param description The description of the event task.
     * @param from        The starting date/time of the event in its original format.
     * @param to          The ending date/time of the event in its original format.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = convertDate(from);
        this.to = convertDate(to);
    }
    public String getFrom() {
        return from;
    }
    public String getTo() {
        return to;
    }

    /**
     * Converts the date to a standard format.
     *
     * @param date The date to be converted.
     * @return The date in a standard format.
     */
    private String convertDate(String date) {
        try {
            LocalDate parsedDate = LocalDate.parse(date, INPUT_DATE_FORMATTER);
            return parsedDate.format(STANDARD_DATE_FORMATTER);
        } catch (Exception e) {
            try {
                LocalDateTime parsedDateTime = LocalDateTime.parse(date, INPUT_DATETIME_FORMATTER);
                return parsedDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
            } catch (Exception ex) {
                return date;
            }
        }
    }
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
