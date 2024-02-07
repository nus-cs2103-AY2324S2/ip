package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class represents a task that spans a specific time period.
 * It is a subclass of the Task class.
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructs an Event object with a description, start time, and end time.
     *
     * @param description A description of the event task.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Converts the Event object to a string that can be saved to a file.
     *
     * @return A formatted string representing the Event object.
     */
    @Override
    public String toFileString() {
        return "E | " + getStatusNumber() + " | " + description + " | "
                + from.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm")) + " - "
                + to.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));
    }

    /**
     * Converts the Event object to a user-friendly string representation.
     *
     * @return A string representing the Event object in a user-friendly format.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[E]").append(super.toString()).append(" (from: ")
                .append(from.format(DateTimeFormatter.ofPattern("MMM d yyyy")));

        if (from.toLocalTime().getHour() != 0 || from.toLocalTime().getMinute() != 0) {
            builder.append(" at ").append(from.format(DateTimeFormatter.ofPattern("hh:mm a")));
        }

        builder.append(" to: ").append(to.format(DateTimeFormatter.ofPattern("MMM d yyyy")));

        if (to.toLocalTime().getHour() != 0 || to.toLocalTime().getMinute() != 0) {
            builder.append(" at ").append(to.format(DateTimeFormatter.ofPattern("hh:mm a")));
        }

        builder.append(")");

        return builder.toString();
    }
}
