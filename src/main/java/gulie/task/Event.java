package gulie.task;

import gulie.GulieException;

import java.time.LocalDate;
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
    public Event(String name, LocalDateTime from, LocalDateTime to) throws GulieException {
        this(name, from, to, false);
    }

    /**
     * A constructor for an Event.
     * @param name
     * @param from
     * @param to
     * @param mark
     */
    public Event(String name, LocalDateTime from, LocalDateTime to, boolean mark) throws GulieException {
        super(name, mark);
        if (from.isAfter(to)) {
            throw new GulieException("It is impossible for an Event to end before it starts.");
        }
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean onDate(LocalDate date) {
        if (from.toLocalDate().equals(date)) {
            return true;
        } else if (to.toLocalDate().equals(date)) {
            return true;
        }
        return date.isAfter(from.toLocalDate()) && date.isBefore(to.toLocalDate());
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
