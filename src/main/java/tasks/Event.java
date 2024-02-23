package tasks;

import java.time.LocalDateTime;

/**
 * represents a task that occurs within a specific time frame.
 * adds functionality to store and retrieve the start and end dates of the event.
 */
public class Event extends Task {
    
    protected LocalDateTime startDate;
    protected LocalDateTime endDate;

    /**
     * Constructs an Event object.
     *
     * @param description The description of the event.
     * @param startDate   The start date of the event.
     * @param endDate     The end date of the event.
     */
    public Event(String description, LocalDateTime startDate, LocalDateTime endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Gets the start date of the event.
     *
     * @return The start date of the event.
     */
    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    /**
     * Gets the end date of the event.
     *
     * @return The end date of the event.
     */
    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)",
                super.toString(), this.startDate.format(Task.OUTPUT_DATE_FORMAT), this.endDate.format(Task.OUTPUT_DATE_FORMAT));
    }
}
