package duke.task;

import duke.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    /** Type icon for Event task */
    private static final String TYPE = "E";
    /** Start date/time of this task */
    protected LocalDateTime start;
    /** End date/time of this task */
    protected LocalDateTime end;

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
        return "[" + TYPE + "]" + super.toString() +
                "(from: " + printStart + " to: " + printEnd + ")";
    }
}
