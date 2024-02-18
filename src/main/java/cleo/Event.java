package cleo;

import java.time.LocalDate;
;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Event-type task with a description, start date, and end date.
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;
    /**
     * Creates a new Event task.
     *
     * @param description The textual description of the Event task
     * @param from Start date of the event in the format "d/M/yyyy"
     * @param to End date of the event in the format "d/M/yyyy"
     * @throws DukeException if there's an issue parsing the provided dates
     */
    Event(String description, String from, String to) throws DukeException {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        try {
            this.from = LocalDate.parse(from, formatter);
            this.to = LocalDate.parse(to, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format. Please use 'd/M/yyyy', e.g., '2/12/2019'.");
        }
    }
    /**
     * Returns a string representation to visually identify an Event task.
     *
     * @return The string "[E]"
     */
    @Override
    public String getTypeIcon() {
        return "[E]";
    }


    /**
     * Returns the start date of the event.
     *
     * @return The start date (LocalDate)
     */
    public LocalDate getFrom() {
        return this.from;
    }
    /**
     * Returns the end date of the event.
     *
     * @return The end date (LocalDate)
     */
    public LocalDate getTo() {
        return this.to;
    }

    /**
     * Returns a formatted string representation of the Event, including description and dates.
     *
     * @return The formatted event string
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return super.toString() + " (from " + this.getFrom().format(formatter) + " to " + this.getTo().format(formatter) + ")";
    }


}
