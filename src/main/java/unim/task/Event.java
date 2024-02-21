package unim.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event - Represents an event task with start and end dates, a subclass of Task.
 */
public class Event extends Task {
    private String fromDate;
    private String toDate;
    private LocalDateTime d1;
    private LocalDateTime d2;

    /**
     * Creates an Event task with a description and date range specified as LocalDate objects.
     *
     * @param description The description of the event.
     * @param d1          The start date of the event as a LocalDate.
     * @param d2          The end date of the event as a LocalDate.
     */
    public Event(String description, LocalDateTime d1, LocalDateTime d2) {
        super(description);
        this.d1 = d1;
        this.d2 = d2;
    }

    @Override
    public String toString() {
        if (d1 != null && d2 != null) {
            return super.getStatusIcon() + "[E] " + getDescription() + " (from: "
                    + d1.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"))
                    + " to: " + d2.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
        }
        return super.getStatusIcon() + "[E] " + getDescription() + " (from: " + fromDate + " to: " + toDate + ")";
    }
}
