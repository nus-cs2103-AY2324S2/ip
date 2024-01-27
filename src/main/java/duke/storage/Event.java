package duke.storage;

/**
 * The Event class defines a 'Event' task used for the application
 *
 * @author Ryan NgWH
 */
public class Event extends Task {
    /**
     * Start date/time of the Event task
     */
    private String startDate;

    /**
     * End date/time of the Event task
     */
    private String endDate;

    /**
     * Create an Event task
     *
     * @param description Description of the event
     * @param startDate   Start date of the event
     * @param endDate     End date of the event
     */
    public Event(String description, String startDate, String endDate) {
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
    public Event(String description, String startDate, String endDate, boolean isDone) {
        super(description, TaskType.EVENT, isDone);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Get the start date of the event
     *
     * @return Start date of the event
     */
    public String getStartDate() {
        return this.startDate;
    }

    /**
     * Get the end date of the event
     *
     * @return End date of the event
     */
    public String getEndDate() {
        return this.endDate;
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
