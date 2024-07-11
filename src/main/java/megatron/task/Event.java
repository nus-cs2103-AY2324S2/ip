package megatron.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Task of event type with start and end dates
 */
public class Event extends Task {
    /** Type icon for Event task */
    private static final String TYPE = "E";
    /** Start date/time of this task */
    protected LocalDateTime start;
    /** End date/time of this task */
    protected LocalDateTime end;

    /**
     * Constructor
     * @param name for name of task
     * @param start for start date/time of task
     * @param end for end date/time of task
     */
    public Event(String name, LocalDateTime start, LocalDateTime end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    @Override
    public String getDetails() {
        return super.getDetails() + ":::" + this.start + ":::" + this.end;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");
        String printStart = start.format(formatter);
        String printEnd = end.format(formatter);
        return "[" + TYPE + "]" + super.toString()
                + "(from: " + printStart + " to: " + printEnd + ")";
    }
}
