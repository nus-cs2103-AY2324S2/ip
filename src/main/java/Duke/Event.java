package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * This class represents an Event task.
 * It extends the Task class with a specific date range and a specific string representation.
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy");

    /**
     * Constructs a new Event task with the specified name and date range.
     *
     * @param name the name of the Event task
     * @param from the start date of the event
     * @param to the end date of the event
     * @throws InvalidDateFormat if the date format is invalid
     */
    public Event(String name, String from, String to) throws InvalidDateFormat {
        super(name.trim());
        try {
            this.from = LocalDate.parse(from.trim());
            this.to = LocalDate.parse(to.trim());
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormat();
        }
    }


    /**
     * Returns a string representation of the Event task.
     * The returned string includes the task type ([E]), the string representation of the superclass, and the date range.
     *
     * @return a string representation of the Event task
     */
    @Override
    public String toString() {
        String date = String.format(" (from: %s to: %s)", this.from.format(FORMATTER), this.to.format(FORMATTER));
        return "[E]" + super.toString() + date;
    }
}