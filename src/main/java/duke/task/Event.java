package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * The class representing an Event task.
 * */
public class Event extends Task {
    /* Type for Event task. */
    public static final String TYPE = "E";
    /* Type indicator for Event task. */
    public static final String TYPE_INDICATOR = "[E]";
    /* Start date of the event. */
    private final LocalDate start;
    /* End date of the event. */
    private final LocalDate end;

    public Event(String description, String start, String end) {
        super(description);
        assert start != null;
        assert end != null;
        this.start = LocalDate.parse(start.replace(" ", ""));
        this.end = LocalDate.parse(end.replace(" ", ""));
    }

    @Override
    public String toString() {
        return TYPE_INDICATOR
            + getDisplay()
            + " "
            + getDescription()
            + "(from: "
            + this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
            + "to: "
            + this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
            + ")";
    }

    @Override
    public String toDbString() {
        return TYPE + super.toDbString() + this.start + "|" + this.end;
    }
}
