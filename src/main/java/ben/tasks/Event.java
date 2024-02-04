package ben.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task in the Ben task management application.
 */
public class Event extends Task {
    protected LocalDate startDate;
    protected LocalDate endDate;

    /**
     * Constructs an Event task with the specified attributes.
     *
     * @param isDone      Indicates if the task is done or not.
     * @param description The description of the event task.
     * @param startDate   The start date of the event.
     * @param endDate     The end date of the event.
     */
    public Event(boolean isDone, String description, LocalDate startDate, LocalDate endDate) {
        super(isDone, description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Converts the Event task to a string representation for saving to a file.
     *
     * @return A string representing the Event task for saving to a file.
     */
    @Override
    public String saveTask() {
        return "E | " + super.saveTask() + " | " + this.startDate + " | " + this.endDate;
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return A string representation of the Event task.
     */
    @Override
    public String toString() {
        String formattedStartDate = this.startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String formattedEndDate = this.endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString() + " (from: " + formattedStartDate + " to: " + formattedEndDate + ")";
    }
}
