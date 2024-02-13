package ukecat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task in the UkeCat application.
 * Inherits from the Task class and includes additional methods specific to Event tasks.
 */
public class Event extends Task {
    private LocalDate start;
    private LocalDate end;

    /**
     * Constructs a new Event task with the specified description, start date, and end date.
     *
     * @param description The description of the Event task.
     * @param start       The start date of the event.
     * @param end         The end date of the event.
     */
    public Event(String description, LocalDate start, LocalDate end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a new Event task with the specified status, description, start date, and end date.
     *
     * @param status      The status of the Event task.
     * @param description The description of the Event task.
     * @param start       The start date of the event.
     * @param end         The end date of the event.
     */
    public Event(TaskStatus status, String description, LocalDate start, LocalDate end) {
        super(status, description);
        this.start = start;
        this.end = end;
    }

    /**
     * Gets the string representation of the start date of the event.
     *
     * @return The start date of the event as a string.
     */
    public String getStart() {
        return this.start.toString();
    }

    /**
     * Gets the string representation of the end date of the event.
     *
     * @return The end date of the event as a string.
     */
    public String getEnd() {
        return this.end.toString();
    }

    /**
     * Returns a string representation of the Event task.
     * The string includes the task type, status icon, description, and event date information.
     *
     * @return A string representation of the Event task.
     */
    @Override
    public String toString() {
        String info = String.format("(from: %s to: %s)",
                start.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                end.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        return String.format("[E]%s %s %s", this.getStatusIcon(), super.toString(), info);
    }
}
