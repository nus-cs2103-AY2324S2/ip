package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 * <p>
 * This class extends the {@link Task} class and is used to represent tasks that
 * have a start and end date. It provides a constructor to create a new event
 * task with a description, start date and end date.
 * </p>
 */
public class Event extends Task {

    protected LocalDate startDate;
    protected LocalDate deadlineDate;

    /**
     * Constructs a new {@code Event} instance with the specified description, start
     * date and end date.
     * 
     * @param description The description of the event task.
     * @param from        The start date of the event task.
     * @param by          The end date of the event task.
     */
    public Event(String description, LocalDate startDate, LocalDate deadlineDate) {
        super(description);
        this.startDate = startDate;
        this.deadlineDate = deadlineDate;
    }

    /**
     * Returns the start date of the event task.
     * 
     * @return The start date of the event task.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Returns the end date of the event task.
     * 
     * @return The end date of the event task.
     */
    public LocalDate getDeadlineDate() {
        return deadlineDate;
    }

    /**
     * Returs the string representation of the event task.
     * 
     * @return The string representation of the event task.
     */
    @Override
    public String toString() {
        String startDateString = startDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String deadlineDateString = deadlineDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return super.getPriorityString() + "[E]" + super.toString() + " (from: " + startDateString + " to: "
                + deadlineDateString + ")";
    }

    /**
     * Returns the string representation of the event task to be saved in the hard
     * disk.
     * 
     * @return The string representation of the event task to be saved in the hard
     *         disk.
     */
    @Override
    public String toFileString() {
        return "E | " + super.toFileString() + " | " + startDate + " | " + deadlineDate;
    }
}
