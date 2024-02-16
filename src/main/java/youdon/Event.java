package youdon;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a start and end.
 * This class extends the Task class and includes methods to represent event tasks,
 * such as obtaining the type icon and converting the task to a string format.
 */
public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructs a new instance of the Event class with the specified description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param start       The start time of the event task.
     * @param end         The end time of the event task.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Gets the type icon for the event task.
     *
     * @return The type icon for the event task.
     */
    public String getTypeIcon() {
        return "E";
    }

    /**
     * Converts the event task to a string format.
     *
     * @return The string representation of the event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String formattedDateTimeStart = this.start.format(formatter);
        String formattedDateTimeEnd = this.end.format(formatter);
        return "[" + this.getTypeIcon() + "][" + this.getStatusIcon() + "] " + this.description.trim()
                + " (from: " + formattedDateTimeStart + " to: " + formattedDateTimeEnd + ")";
    }
}
