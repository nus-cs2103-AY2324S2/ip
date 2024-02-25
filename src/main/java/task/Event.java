package task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Event is a Task that has description, start date/time, end date/time.
 */
public class Event extends Task {

    private LocalDate startDate;
    private LocalTime startTime;
    private LocalDate endDate;
    private LocalTime endTime;

    /**
     * The constructor of Event.
     *
     * @param description Description of the task.
     * @param startDate Start date.
     * @param startTime Start time.
     * @param endDate End date of the event.
     * @param endTime Start time of the event.
     */
    public Event(String description, LocalDate startDate, LocalTime startTime,
                 LocalDate endDate, LocalTime endTime) {
        super(description);
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + startDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                + " " + startTime + " | to: "
                + endDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
                + " " + endTime + ")";
    }
}
