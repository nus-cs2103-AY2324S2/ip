package denify.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import denify.exception.DenifyException;

/**
 * The `Event` class represents a task that spans a specific duration in Denify.
 */
public class Event extends Task {
    /**
     * The formatted string representation of the start time.
     */
    protected String from;
    /**
     * The formatted string representation of the end time.
     */
    protected String to;
    /**
     * The input date-time formatter.
     */
    protected DateTimeFormatter input = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    /**
     * The output date-time formatter for display.
     */
    protected DateTimeFormatter output = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    /**
     * Constructs an `Event` with the given description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from The start time of the event in yyyy-MM-dd HH:mm format.
     * @param to The end time of the event in yyyy-MM-dd HH:mm format.
     * @throws DenifyException If there is an issue parsing the time or the format is invalid.
     */
    public Event(String description, String from, String to) throws DenifyException {
        super(description);
        try {
            LocalDateTime date1 = LocalDateTime.parse(from, input);
            LocalDateTime end1 = LocalDateTime.parse(to, input);
            this.from = date1.format(output);
            this.to = end1.format(output);
            if (!isBeforeDateTime(date1, end1)) {
                throw new DenifyException("Invalid date range. 'From' should be before 'To'.");
            }
        } catch (DateTimeParseException | StringIndexOutOfBoundsException e) {
            throw new DenifyException("Invalid time format. Please use yyyy-MM-dd HH:mm.");
        }
    }
    /**
     * Checks if the start date and time is before or equal to the end date and time.
     *
     * @param from The start date and time.
     * @param to   The end date and time.
     * @return True if the start date and time is before or equal to the end date and time, false otherwise.
     */
    private boolean isBeforeDateTime(LocalDateTime from, LocalDateTime to) {
        return from.isBefore(to) || from.isEqual(to);
    }
    /**
     * Returns a string representation of the event task, including its status icon and the start and end times.
     *
     * @return String representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
    /**
     * Returns a formatted string for writing the event task to a file.
     *
     * @return Formatted string for file representation.
     */
    @Override
    public String toFileString() {
        return "E" + super.toFileString() + " | " + LocalDateTime.parse(this.from, output).format(input) + " - "
                + LocalDateTime.parse(this.to, output).format(input);
    }
    /**
     * Indicates whether some other object is "equal to" this one. Equality is based on task properties.
     *
     * @param obj The reference object with which to compare.
     * @return True if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Event event = (Event) obj;
        return this.isDone == event.isDone
                && this.description.equals(event.description)
                && this.from.equals(event.from)
                && this.to.equals(event.to);
    }
    /**
     * Returns a hash code value for the object.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.description, this.isDone, this.from, this.to);
    }
}
