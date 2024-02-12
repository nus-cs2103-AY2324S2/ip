package BotChat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start and end date/time in the botChat application.
 * Extends the base Task class.
 */
public class Event extends Task {
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

        // Assert that description is not null or empty
        assert description != null && !description.isEmpty() : "Description should not be null or empty";

        // Assert that from and to are not null or empty
        assert from != null && !from.isEmpty() : "Start date/time should not be null or empty";
        assert to != null && !to.isEmpty() : "End date/time should not be null or empty";

        if (isValidDateFormat(from) && isValidDateFormat(to)) {
            this.from = from;
            this.to = to;
        } else {
            this.from = convertDate(from);
            this.to = convertDate(to);
        }
    }
    public String getFrom() {
        return from;
    }
    public String getTo() {
        return to;
    }

    /**
     * Checks if the provided date is in a valid format.
     *
     * @param by The date to be checked.
     * @return True if the date is in a valid format, false otherwise.
     */
    private boolean isValidDateFormat(String by) {
        // Assert that by is not null or empty
        assert by != null && !by.isEmpty() : "Date should not be null or empty";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        try {
            LocalDate date = LocalDate.parse(by, formatter);
            String formattedDateTime = date.format(formatter);
            return formattedDateTime.equals(by);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Converts the date to a standard format.
     *
     * @param by The date to be converted.
     * @return The date in a standard format.
     */
    private String convertDate(String by) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate date = LocalDate.parse(by, inputFormatter);
            return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (Exception e) {
            try {
                LocalDateTime dateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                return dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
            } catch (Exception ex) {
                return by;
            }
        }
    }
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
