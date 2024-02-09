package lai.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event, which is a task with a start and end date.
 */
public class Event extends Task {
    /**
     * The start date of the event.
     */
    protected LocalDate from;

    /**
     * The end date of the event.
     */
    protected LocalDate to;

    /**
     * Constructs a new Event with the specified description, start date, and end date.
     * The start and end dates are parsed from strings. If parsing fails using ISO-8601 format, an attempt is made using
     * "dd MMM yyyy" format.
     *
     * @param description The description of the event.
     * @param from The start date of the event in ISO-8601 format or "dd MMM yyyy" format.
     * @param to The end date of the event in ISO-8601 format or "dd MMM yyyy" format.
     * @throws DateTimeParseException If the start or end date strings cannot be parsed.
     */
    public Event(String description, String from, String to) {
        super(description);
        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            this.from = LocalDate.parse(from, DateTimeFormatter.ofPattern("dd MMM yyyy"));
            this.to = LocalDate.parse(to, DateTimeFormatter.ofPattern("dd MMM yyyy"));
        }
    }

    /**
     * Constructs a new Event with the specified description, initial completion status, start date, and end date.
     * The start and end dates are parsed from strings. If parsing fails using ISO-8601 format, an attempt is made using
     * "dd MMM yyyy" format.
     *
     * @param description The description of the event.
     * @param isDone The initial completion status of the event. True if the event is initially marked as done, false otherwise.
     * @param from The start date of the event in ISO-8601 format or "dd MMM yyyy" format.
     * @param to The end date of the event in ISO-8601 format or "dd MMM yyyy" format.
     * @throws DateTimeParseException If the start or end date strings cannot be parsed.
     */
    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            this.from = LocalDate.parse(from, DateTimeFormatter.ofPattern("dd MMM yyyy"));
            this.to = LocalDate.parse(to, DateTimeFormatter.ofPattern("dd MMM yyyy"));
        }
    }

    /**
     * Returns a string representation of the event, including its type, completion status, description, start date, and
     * end date.
     * The start and end dates are formatted as "dd MMM yyyy".
     *
     * @return A string in the format "[E][completion_status] description (from: start_date to: end_date)", where
     * completion_status is "X" if the event is done, or a space character otherwise.
     */
    @Override
    public String toString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return "[E]" + super.toString() + " (from: " + from.format(dateFormat) + " to: " + to.format(dateFormat) + ")";
    }
}
