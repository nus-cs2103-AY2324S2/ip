package gulie.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A task that has a start and end time.
 */
public class Event extends Task {
    private LocalDateTime from, to;

    /**
     * A constructor for an Event.
     * @param name
     * @param from
     * @param to
     */
    public Event(String name, LocalDateTime from, LocalDateTime to) {
        this(name, from, to, false);
    }

    /**
     * A constructor for an Event.
     * @param name
     * @param from
     * @param to
     * @param mark
     */
    public Event(String name, LocalDateTime from, LocalDateTime to, boolean mark) {
        super(name, mark);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toSaveString() {
        return String.format("E\t%s\t%s\t%s", super.toSaveString(), from, to);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }

    @Override
    public String toString(DateTimeFormatter dtf) {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), dtf.format(from), dtf.format(to));
    }
}
