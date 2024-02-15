package seedu.duke.task;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task created by the user. A <code>Event</code> represents a task containing
 * additional information for start date of the event and the end date of the event.
 */
public class Event extends Task {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String startDateString;
    private String endDateString;

    /**
     * Constructor of Event object
     *
     * @param description The description of the event
     * @param hasDone     Whether the event is done
     * @param startDate   the start date of the event
     * @param endDate     the end date of the event
     */
    public Event(String description, boolean hasDone, LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.startDateString = startDate.format(DateTimeFormatter.ofPattern("MM dd yyyy HH:mm"));
        this.endDateString = endDate.format(DateTimeFormatter.ofPattern("MM dd yyyy HH:mm"));
        super.setDescription(description);
        super.setHasDone(hasDone);
    }

    /**
     * Returns the start date of an event
     *
     * @return The start date of the event
     */
    public LocalDateTime getStartDate() {
        return startDate;
    }

    /**
     * Returns the end date of the event
     *
     * @return The end date of the event
     */
    public LocalDateTime getEndDate() {
        return endDate;
    }

    /**
     * Returns the start date string representation
     *
     * @return The start date string representation
     */
    public String getStartDateString() {
        return startDateString;
    }

    /**
     * Returns the end date string representation
     *
     * @return The end date representation
     */
    public String getEndDateString() {
        return endDateString;
    }


    /**
     * Returns the string representation of an event
     *
     * @return The string representation of an event task
     */
    @Override
    public String toString() {

        return "[E]" + super.toString() + " (from: " + this.startDateString + " to: " + this.endDateString + ")";
    }
}
