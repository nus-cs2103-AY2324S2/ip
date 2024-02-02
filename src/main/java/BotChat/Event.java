package BotChat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start and end date/time in the botChat application.
 * Extends the base Task class.
 */
public class Event extends Task {

    /**
     * The starting date/time of the event in its original format.
     */
    protected String from;

    /**
     * The ending date/time of the event in its original format.
     */
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
        if (isValidDateFormat(from) && isValidDateFormat(to)) {
            this.from = from;
            this.to = to;
        } else {
            this.from = convertDate(from);
            this.to = convertDate(to);
        }
    }

    /**
     * Gets the starting date/time of the event in its original format.
     *
     * @return The starting date/time of the event.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Gets the ending date/time of the event in its original format.
     *
     * @return The ending date/time of the event.
     */
    public String getTo() {
        return to;
    }

    private boolean isValidDateFormat(String by) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        try {
            LocalDate date = LocalDate.parse(by, formatter);
            String formattedDateTime = date.format(formatter);
            return formattedDateTime.equals(by);
        } catch (Exception e) {
            return false;
        }
    }

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

    /**
     * Overrides the toString method to provide a string representation of the Event object.
     *
     * @return A string representation of the Event object.
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}