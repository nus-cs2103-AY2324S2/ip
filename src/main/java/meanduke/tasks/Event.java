package meanduke.tasks;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * This class represents a Task with a start and end duration
 */
public class Event extends Task {

    private static final String TYPE_SYMBOL = "[E]";
    private final LocalDate fromDate;
    private final LocalDate toDate;
    private final LocalTime fromTime;
    private final LocalTime toTime;

    /**
     * Constructs a new Event with the specified description, completion state, start, and end period.
     *
     * @param description Description of the Event
     * @param isDone      boolean value that determines if the initialised Event is completed or not
     * @param fromDate    start date of the Event in the format "YYYY-MM-DD"
     * @param fromTime    start time of the Event in the format "HH:MM", or null
     * @param toDate      end date of the Event in the format "YYYY-MM-DD"
     * @param toTime      end time of the Event in the format "HH:MM", or null
     */
    public Event(String description, boolean isDone, LocalDate fromDate,
                 LocalTime fromTime, LocalDate toDate, LocalTime toTime) {
        super(description, TYPE_SYMBOL, isDone);
        this.fromDate = fromDate;
        this.fromTime = fromTime;
        this.toDate = toDate;
        this.toTime = toTime;
    }

    @Override
    public String saveString() {
        return "EVENT" + "\n"
                + super.saveString() + "\n"
                + this.fromDate
                + (this.fromTime == null ? "" : ";" + this.fromTime) + "\n"
                + this.toDate
                + (this.toTime == null ? "" : ";" + this.toTime);
    }

    @Override
    public String toString() {
        String fromString = this.fromDate.getDayOfMonth() + " "
                + this.fromDate.getMonth().toString() + " "
                + this.fromDate.getYear()
                + (this.fromTime == null ? "" : " " + this.fromTime);
        String toString = this.toDate.getDayOfMonth() + " "
                + this.toDate.getMonth().toString() + " "
                + this.toDate.getYear()
                + (this.toTime == null ? "" : " " + this.toTime);
        return super.toString() + " (" + fromString + " - " + toString + ")";
    }
}
