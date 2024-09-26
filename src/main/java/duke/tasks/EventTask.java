package duke.tasks;

import java.time.LocalDate;

/**
 * The EventTask class represents a task that occurs within a specified time frame.
 * It extends the Task class and adds attributes for the start and end dates of the event.
 * <p>
 * An event task includes a description, a start date, and an end date.
 * </p>
 *
 * @author Justin Leng Chern Harn
 * @version 1.0
 * @see duke.tasks.Task
 */
public class EventTask extends Task {
    protected LocalDate startBy;
    protected LocalDate endBy;

    /**
     * Constructs an EventTask with the specified description, start date, and end date.
     *
     * @param description the description of the event task.
     * @param startBy the start date of the event.
     * @param endBy the end date of the event.
     */
    public EventTask(String description, LocalDate startBy, LocalDate endBy) {
        super(description);
        this.startBy = startBy;
        this.endBy = endBy;
    }

    /**
     * Returns the start date of the event.
     *
     * @return the start date of the event.
     */
    public LocalDate getStartBy() {
        return this.startBy;
    }

    /**
     * Returns the end date of the event.
     *
     * @return the end date of the event.
     */
    public LocalDate getEndBy() {
        return this.endBy;
    }
    @Override
    public String toString() {
        return "E" + " | " + super.toString() + " | " + "(from: " + getStartBy() + " to: " + getEndBy() + ")";
    }
}
