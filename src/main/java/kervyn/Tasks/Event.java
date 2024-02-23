package kervyn.Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task, which is an extension of the Task class with additional start and end dates.
 */
public class Event extends Task {
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    /**
     * Constructs an Event task with a description, status, start date, and end date.
     *
     * @param description The description of the event task.
     * @param status The completion status of the event task.
     * @param startDate The LocalDateTime representing the start date of the event.
     * @param endDate The LocalDateTime representing the end date of the event.
     */
    public Event(String description, boolean isCompleted, LocalDateTime startDate, LocalDateTime endDate) {
        super(description, isCompleted, Type.EVENT);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Gets the formatted start date of the event.
     *
     * @return A string representing the formatted start date (e.g., "Jan 1 2020, 5PM").
     */
    public String getStartDate() {
        String formattedStartDate = this.startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy, ha"));
        return formattedStartDate;
    }

    /**
     * Gets the formatted end date of the event.
     *
     * @return A string representing the formatted end date (e.g., "Jan 2 2020, 5PM").
     */
    public String getEndDate() {
        String formattedEndDate = this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy, ha"));
        return formattedEndDate;
    }

    /**
     * Converts the task to a string representation, including type, status, and description.
     *
     * @return A string representation of the Event task.
     */
    @Override
    public String toString() {
        char check = this.getCompleted() ? 'X' : ' ';
        return "\t[" + this.getCapitalType() + "]" + " [" + check + "] " + this.getDescription() + " (from: " + this.getStartDate() + " to: " + this.getEndDate() + ")";
    }

}
