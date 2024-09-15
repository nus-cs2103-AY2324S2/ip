package capone.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a from and to date entry.
 * Extends the Task class.
 *
 * @author Tay Rui-Jie
 */
public class Event extends Task {
    /**
     * The start date and time of the event in string format.
     * Used when fromDate is not provided in LocalDateTime format.
     */
    protected String fromDateTimeString;

    /**
     * The end date and time of the event in string format.
     * Used when toDate is not provided in LocalDateTime format.
     */
    protected String toDateTimeString;

    /** The start date and time of the event in LocalDateTime format. */
    private LocalDateTime fromDateTime;

    /** The end date and time of the event in LocalDateTime format. */
    private LocalDateTime toDateTime;

    /**
     * Constructs an Event task with the specified description,
     * status, start date, and end date in string format.
     *
     * @param description The description of the task.
     * @param status      The completion status of the task.
     * @param fromDate    The start date and time of the event in string format.
     * @param toDate      The end date and time of the event in string format.
     */
    public Event(String description, boolean status, String fromDate, String toDate) {
        super(TaskType.EVENT, description, status);
        this.fromDateTimeString = fromDate;
        this.toDateTimeString = toDate;
    }

    /**
     * Constructs an Event task with the specified description, status,
     * start date, and end date in LocalDateTime format.
     *
     * @param description The description of the task.
     * @param status      The completion status of the task.
     * @param fromDate    The start date and time of the event in LocalDateTime format.
     * @param toDate      The end date and time of the event in LocalDateTime format.
     */
    public Event(String description, boolean status, LocalDateTime fromDate, LocalDateTime toDate) {
        super(TaskType.EVENT, description, status);
        this.fromDateTime = fromDate;
        this.toDateTime = toDate;
    }

    /**
     * Gets the formatted start date and time of the event as a string.
     *
     * @return The formatted start date and time string.
     */
    public String getFromDateTime() {
        if (this.fromDateTime != null) {
            return this.fromDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        }
        return this.fromDateTimeString;
    }

    /**
     * Gets the formatted end date and time of the event as a string.
     *
     * @return The formatted end date and time string.
     */
    public String getToDateTime() {
        if (this.toDateTime != null) {
            return this.toDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        }
        return this.toDateTimeString;
    }

    /**
     * Overrides the toString method to represent the Event task as a string.
     *
     * @return A formatted string representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.getFromDateTime()
                + " to: " + this.getToDateTime() + ")";
    }
}
