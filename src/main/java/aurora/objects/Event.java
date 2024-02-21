package aurora.objects;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** The event class represents events with a start and end datetime. */
public class Event extends Task{

    /** Starting datetime of the event. */
    private LocalDateTime startDate;

    /** Ending datetime of the event. */
    private LocalDateTime endDate;

    private static final String TASK_TYPE_FOR_FILE = "E";
    private static final String TASK_TYPE = "[E]";

    /**
     * Constructs an Event object.
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

    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    /**
     * Returns a String representation of a LocalDateTime object.
     *
     * @return Returns String representation of a LocalDateTime object.
     */
    private String dateToString(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
        return date.format(formatter);
    }

    @Override
    public String toFileString() {
        String isDone = this.getStatus() ? "1" : "0";
        String description = super.toFileString();
        String startDate = dateToString(this.startDate);
        String endDate = dateToString(this.endDate);
        return TASK_TYPE_FOR_FILE + " | " + isDone + " | " + description + " | " + startDate + " | " + endDate;
    }

    @Override
    public String toString() {
        String startDate = dateToString(this.startDate);
        String endDate = dateToString(this.endDate);
        String eventString = TASK_TYPE + super.toString() + " (from: " + startDate + " to: " + endDate + ")";
        return eventString;
    }

}
