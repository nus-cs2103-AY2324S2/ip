package task;


import andelu.PriorityLevel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

/**
 * A class to create Event Object.
 * A subclass of Task.
 */
public class Event extends Task {

    /** The start DateTime for this Event. */
    private LocalDateTime start;

    /** The end DateTime for this Event. */
    private LocalDateTime end;


    /**
     * A constructor to create the Event Object.
     *
     * @param description The title of the Event.
     * @param isDone The status of the Event.
     * @param start The start DateTime of the Event.
     * @param end The end DateTime of the Event.
     */
    public Event(String description, boolean isDone, PriorityLevel priorityLevel, LocalDateTime start, LocalDateTime end) {
        super(description, isDone, priorityLevel);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the start DateTime of the Event.
     *
     * @return The start DateTime.
     */
    public LocalDateTime getStart() {
        return this.start;
    }

    /**
     * Returns the end DateTime of the Event.
     *
     * @return The end DateTime.
     */
    public LocalDateTime getEnd() {
        return this.end;
    }

    /**
     * Prints the information of the Event Object.
     *
     * @return the information of the Event.
     */
    @Override
    public String toString() {
        List<DateTimeFormatter> formatters = Arrays.asList(
                DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"),
                DateTimeFormatter.ofPattern("MMM dd yyyy'T'HH:mm")
        );

        String startString = "";
        String endString = "";

        for (DateTimeFormatter formatter : formatters) {
            try {
                startString = this.start.format(formatter);
                endString = this.end.format(formatter);
                break;
            } catch (DateTimeParseException e) {
                // Continue next format
            }
        }

        return "[E][" + super.getStatusIcon() + "] "
                + super.getDescription()
                + " (from: " + startString + " to: "
                + endString + ")"
                + " (" + super.getPriorityLevel() + " priority)";
    }
}
