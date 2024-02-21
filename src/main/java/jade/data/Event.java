package jade.data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jade.exception.JadeException;

/**
 * The <code>Event</code> object represents a user task with a start dateTime and an end dateTime.
 */
public class Event extends Task {
    public static String DATE_UNEXPECTED_ERROR = "Your date range is invalid, please reenter a valid start and end date.";
    protected LocalDateTime startDateTime;
    protected LocalDateTime endDateTime;

    /**
     * Class constructor specifying the event description, start dateTime, and end dateTime.
     */
    public Event(String description, LocalDateTime startDateTime, LocalDateTime endDateTime) throws JadeException {
        super(description);
        if (endDateTime.isBefore(startDateTime)) {
            throw new JadeException(DATE_UNEXPECTED_ERROR);
        }
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Another Event constructor.
     * Specifying the deadline description, start dateTime, end dateTime, and the completion status.
     */
    public Event(String description, LocalDateTime startDateTime, LocalDateTime endDateTime, boolean isDone) {
        super(description, isDone);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }


    /**
     * Returns a formatted string of the LocalDateTime object.
     */
    public String dateTimeFormatter(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a"));
    }

    /**
     * {@inheritDoc}
     * Checks whether the event dateTimes include the given date,
     * both startDateTime and endDateTime inclusively.
     */
    @Override
    public boolean isSameDate(LocalDate date) {
        return (date.isBefore(endDateTime.toLocalDate()) && date.isAfter(startDateTime.toLocalDate()))
                || date.equals(startDateTime.toLocalDate()) || date.equals(endDateTime.toLocalDate());
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                dateTimeFormatter(startDateTime), dateTimeFormatter(endDateTime));
    }

    /**
     * {@inheritDoc}
     * Adds the start dateTime and end dateTime at the end.
     */
    @Override
    public String taskFormatter() {
        return String.format("E | %s | %s | %s - %s\n", statusFormatter(),
                description, dateTimeFormatter(startDateTime), dateTimeFormatter(endDateTime));
    }
}
