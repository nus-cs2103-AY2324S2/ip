package atsisbot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event atsisbot.task. Inherits from the Task class.
 */
public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Constructs an Event object with the given description and fromTo details.
     *
     * @param description The description of the event.
     * @param startTime   The start time of the event.
     * @param endTime     The end time of the event.
     */
    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns a string representation of the Event object.
     *
     * @return A string representation of the Event object.
     */
    @Override
    public String toString() {
        return "[E]["
                + this.getStatus().getStatusIcon()
                + "] "
                + this.getDescription()
                + String.format(
                " (from: %s to %s) - %s\n",
                this.startTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")),
                this.endTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")),
                this.priority.toString());
    }

    /**
     * Encodes the Event object into a string representation.
     * The encoded string follows the format: "E | status | description | start time | end time",
     * where status is "1" if the event is done, and "0" otherwise.
     * The start time and end time are formatted using the pattern "dd/MM/yyyy HH:mm".
     *
     * @return the encoded string representation of the Event object
     */
    @Override
    public String encode() {
        String status = this.getStatus().isDone() ? "1" : "0";
        return "E | "
                + status
                + " | "
                + this.description
                + " | "
                + String.format(
                "%s | %s | %s",
                this.startTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                this.endTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
                this.priority.toString());
    }
}
