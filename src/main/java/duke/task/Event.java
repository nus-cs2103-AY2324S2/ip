package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A type of task with a LocalDate variable from and to, to represent the event starting and
 * end times.
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    public Event(String desc, String from, String to) {
        super(desc);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    public Event(String desc, boolean isDone, String from, String to) {
        super(desc, isDone);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    public String toStore() {
        // need to store status as well
        return "E | " + super.toStore() + " | " + from + " | " + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
