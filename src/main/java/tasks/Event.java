package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import parsing.DateTimeParser;

/**
 * Represents a task with an event.
 */
public class Event extends Task {

    protected LocalDateTime fromWhen;
    protected LocalDateTime toWhen;
    protected DateTimeParser dateTimeParser;

    /**
     * Constructs an Event object with the specified name and event timings.
     *
     * @param name the name of the event
     * @param fromWhenString the string representing the starting date and time of the event
     * @param toWhenString the string representing the ending date and time of the event
     */
    public Event(String name, String fromWhenString, String toWhenString) {
        super(name);
        this.dateTimeParser = new DateTimeParser();
        this.fromWhen = this.dateTimeParser.parseDateTime(fromWhenString);
        this.toWhen = this.dateTimeParser.parseDateTime(toWhenString);
    }

    /**
     * Returns a string representation of the Event object.
     *
     * @return a string containing task type, name, start time, and end time
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + fromWhen.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"))
                + " to: " + toWhen.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }

    /**
     * Returns a string formatted for writing to file.
     *
     * @return a string containing task type, name, start time, and end time in a specific format
     */
    @Override
    public String toWrite() {
        return "E | " + super.toWrite() + " | " + fromWhen.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))
                + " to " + toWhen.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }
}
