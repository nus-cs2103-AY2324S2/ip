package aurora.objects;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The event class is a representation of an event task with a start and end date & time.
 */
public class Event extends Task{

    /** Starting time and date of the event. */
    private LocalDateTime startDate;

    /** Ending time and date of the event. */
    private LocalDateTime endDate;

    /**
     * Constructor for the Event class.
     *
     * @param description: Description of the event.
     * @param startDate: Starting date and time of the event.
     * @param endDate: Ending date and time of the event.
     */
    public Event(String description, LocalDateTime startDate, LocalDateTime endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Getter for start date.
     *
     * @return The start date of the event.
     */
    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    /**
     * Getter for end date.
     *
     * @return The end date of the event.
     */
    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    /**
     * Formats the local datetime to string.
     *
     * @return Date in String format
     */
    private String dateToString(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        return date.format(formatter);
    }

    @Override
    public String toFileString() {
        String taskType = "E";
        String isDone = this.getStatus() ? "1" : "0";
        String description = super.toFileString();
        String startDate = dateToString(this.startDate);
        String endDate = dateToString(this.endDate);
        return taskType + " | " + isDone + " | " + description + " | " + startDate + " | " + endDate;
    }

    @Override
    public String toString() {
        String taskType = "[E]";
        String startDate = dateToString(this.startDate);
        String endDate = dateToString(this.endDate);
        String eventString = taskType + super.toString() + " (from: " + startDate + " to: " + endDate + ")";
        return eventString;
    }

}
