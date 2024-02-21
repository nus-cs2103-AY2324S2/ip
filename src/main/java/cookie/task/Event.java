package cookie.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event task.
 */
public class Event extends Task {

    protected LocalDate fromDate;
    protected String fromTime;
    protected LocalDate toDate;
    protected String toTime;

    /**
     * Constructs an Event object with the given description, from date and time and to date and time.
     *
     * @param description The description of the event task.
     * @param fromDate The start date of the event task.
     * @param fromTime The time at which the event task starts,
     * @param toDate The end date of the event task.
     * @param toTime The time at which the event task ends.
     */
    public Event(String description, LocalDate fromDate, String fromTime, LocalDate toDate, String toTime) {
        super(description);
        this.fromDate = fromDate;
        this.fromTime = fromTime;
        this.toDate = toDate;
        this.toTime = toTime;

        assert fromDate.isBefore(toDate): "event cannot be from a later date to an earlier date";
    }

    /**
     * Constructs an Event object with the given description, tag, from date and time and to date and time.
     *
     * @param description The description of the event task.
     * @param tag The tag of the event task.
     * @param fromDate The start date of the event task.
     * @param fromTime The time at which the event task starts,
     * @param toDate The end date of the event task.
     * @param toTime The time at which the event task ends.
     */
    public Event(String description, String tag, LocalDate fromDate, String fromTime, LocalDate toDate, String toTime) {
        super(description, tag);
        this.fromDate = fromDate;
        this.fromTime = fromTime;
        this.toDate = toDate;
        this.toTime = toTime;

        assert fromDate.isBefore(toDate): "event cannot be from a later date to an earlier date";
    }

    @Override
    public String toString() {
        String status = getStatusIcon();

        String eventStatus = "[E][" + status + "] ";
        String description = super.toString();
        String tag = super.tagString() + "\n";
        String from = fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " " + fromTime;
        String to = " -> " + toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + toTime;

        return eventStatus + description + tag + from + to;
    }

    public String toFileString() {
        String eventStatus = "E | " + (isDone ? "1" : "0");
        String desc = " | " + description + " | ";
        String from = fromDate + " " + fromTime + " ";
        String to = toDate + " " + toTime;
        String tg = " | " + tag + " | ";
        return eventStatus + desc + from + to + tg;
    }
}
