package duke.storage;

import java.time.Instant;

/**
 * The Event class defines a 'Event' task used for the application
 *
 * @author Ryan NgWH
 */
public class Event extends Task {
    /**
     * Start date/time of the Event task
     */
    private Instant startDate;

    /**
     * End date/time of the Event task
     */
    private Instant endDate;

    /**
     * Create an Event task
     *
     * @param description Description of the event
     * @param startDate   Start date of the event
     * @param endDate     End date of the event
     */
    public Event(String description, Instant startDate, Instant endDate) {
        super(description, TaskType.EVENT, false);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Create an Event task
     *
     * @param description Description of the event
     * @param startDate   Start date of the event
     * @param endDate     End date of the event
     * @param isDone      Status of the event
     */
    public Event(String description, Instant startDate, Instant endDate, boolean isDone) {
        super(description, TaskType.EVENT, isDone);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Create an Event task
     *
     * @param description Description of the event
     * @param startDate   Start date of the event (in epoch milliseconds)
     * @param endDate     End date of the event (in epoch milliseconds)
     * @param isDone      Status of the event
     */
    public Event(String description, long startDate, long endDate, boolean isDone) {
        super(description, TaskType.EVENT, isDone);
        this.startDate = Instant.ofEpochMilli(startDate);
        this.endDate = Instant.ofEpochMilli(endDate);
    }

    /**
     * Get the start date of the event
     *
     * @return Start date of the event
     */
    public long getStartDate() {
        return this.startDate.toEpochMilli();
    }

    /**
     * Get the end date of the event
     *
     * @return End date of the event
     */
    public long getEndDate() {
        return this.endDate.toEpochMilli();
    }

    /**
     * String representation of an event
     *
     * @return String representation of the event
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), startDate, endDate);
    }
}
