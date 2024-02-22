package nollid.tasks;

import java.time.LocalDateTime;
import java.util.ArrayList;

import nollid.Parser;
import nollid.exceptions.InvalidArgumentException;
import nollid.exceptions.NollidException;

/**
 * Event class represents a task with a specified start and end time.
 * It extends the Task class and includes additional functionality for handling events.
 */
public class Event extends Task {
    /**
     * The start time of the event represented as LocalDateTime.
     */
    protected LocalDateTime from;
    /**
     * The end time of the event represented as LocalDateTime.
     */
    protected LocalDateTime to;

    /**
     * Creates a new Event with a specified description, start, and end time.
     *
     * @param description The description of the event.
     * @param from        The start time of the event represented as LocalDateTime.
     * @param to          The end time of the event represented as LocalDateTime.
     * @throws NollidException if the end time is before the start time.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) throws NollidException {
        super(description);

        if (from.isAfter(to)) {
            throw new InvalidArgumentException("Start time and date must be before end time and date.");
        }
        this.from = from;
        this.to = to;
    }

    /**
     * Creates a new Event with a specified description, start, and end time.
     *
     * @param description The description of the event.
     * @param from        The start time of the event represented as LocalDateTime.
     * @param to          The end time of the event represented as LocalDateTime.
     * @throws NollidException if the end time is before the start time.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to,
                 ArrayList<String> tags) throws NollidException {
        super(description, tags);

        if (from.isAfter(to)) {
            throw new InvalidArgumentException("Start time and date must be before end time and date.");
        }
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the start time of the event.
     *
     * @return The start time as a LocalDateTime object.
     */
    public LocalDateTime getFrom() {
        return this.from;
    }

    /**
     * Gets the end time of the event.
     *
     * @return The end time as a LocalDateTime object.
     */
    public LocalDateTime getTo() {
        return this.to;
    }

    /**
     * Gets the formatted string representation of the start time.
     *
     * @return The start time formatted as a string using DATE_OUTPUT_FORMAT and TIME_FORMAT from Parser.
     */
    public String getFromString() {
        return this.from.format(Parser.DATE_OUTPUT_FORMAT) + " " + this.from.format(Parser.TIME_FORMAT);
    }

    /**
     * Gets the formatted string representation of the end time.
     *
     * @return The end time formatted as a string using DATE_OUTPUT_FORMAT and TIME_FORMAT from Parser.
     */
    public String getToString() {
        return this.to.format(Parser.DATE_OUTPUT_FORMAT) + " " + this.to.format(Parser.TIME_FORMAT);
    }

    /**
     * Overrides the toString method to provide a string representation of the Event object.
     *
     * @return A formatted string representing the Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.getFromString() + " to: " + this.getToString() + ")"
                + getTagsString();
    }
}
