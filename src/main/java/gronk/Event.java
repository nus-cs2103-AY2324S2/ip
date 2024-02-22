package gronk;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Event class.
 * Subclass of Task, with two dates representing a window
 * for completion of the task.
 */
public class Event extends Task {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private LocalDate startTime;
    private LocalDate endTime;

    /**
     * Constructor for Event object.
     * @param d String containing description of Task.
     * @param s Integer representing completion status.
     * @param st String representing start time of Task.
     * @param et String representing end time of Task.
     */
    public Event(String d, int s, LocalDate st, LocalDate et) {
        super(d, s);
        this.startTime = st;
        this.endTime = et;
    }

    /**
     * Factory constructor for Event.
     *
     * @param description String containing description of Task.
     * @param status Integer representing completion status.
     * @param start String representing start time of Task.
     * @param end String representing end time of Task.
     * @return New Event object.
     */
    public static Event createEvent(String description, int status, String start, String end) {
        try {
            LocalDate startTime = LocalDate.parse(start, DATE_FORMAT);
            LocalDate endTime = LocalDate.parse(end, DATE_FORMAT);
            return new Event(description, status, startTime, endTime);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public String getTime() {
        String st = this.getStartTime();
        String et = this.getEndTime();
        return "\tAny time from " + st + " to " + et;
    }

    public String getStartTime() {
        return this.startTime.format(DATE_FORMAT);
    }

    public String getEndTime() {
        return this.endTime.format(DATE_FORMAT);
    }


    @Override
    public String statusMessage() {
        if (this.getStatus() == 0) {
            return "Well done! Event: " + this.getDesc() + " finished.";
        } else {
            return "Event updated. Event: " + this.getDesc() + " marked as incomplete.";
        }
    }

    @Override
    public String saveFormat() {
        return Integer.toString(this.getStatus())
                + "," + this.getDesc()
                + "," + this.startTime.format(DATE_FORMAT)
                + "," + this.endTime.format(DATE_FORMAT);
    }

    @Override
    public String toString() {
        if (this.getStatus() == 0) {
            return "[E] [ ] " + this.getDesc() + " (during: " + this.getStartTime() + "-" + this.getEndTime() + ")";
        } else {
            return "[E] [X] " + this.getDesc() + " (during: " + this.getStartTime() + "-" + this.getEndTime() + ")";
        }
    }
}
