package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * An encapsulation of an event type task.
 *
 * @author Lim Zi Jia
 */
public class Event extends Task {
    /** The time of the start of the event. */
    private final LocalDate from;
    /** The time of the end of the event. */
    private final LocalDate to;

    /**
     * Constructor for Event.
     * @param done True if the task is done.
     * @param name Name of the Task.
     * @param from Time of the start of event.
     * @param to   Time of the end of event.
     */
    public Event(boolean done, String name, String from, String to) {
        super(done, name);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    /**
     * Constructor for Event.
     * @param name Name of the Task.
     * @param from Time of the start of event.
     * @param to   Time of the end of event.
     */
    public Event(String name, String from, String to) {
        super(name);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    /**
     * Checks if from < to.
     *
     * @return True if the dates are in the correct order.
     */
    public boolean isCorrectOrder() {
        return this.to.isAfter(this.from) | this.to.isEqual(this.from);
    }

    @Override
    public String toSavedString() {
        return String.format("E,%s,%s,%s,%s",
                this.done ? '1' : '0',
                this.name,
                this.from.format(DateTimeFormatter.ISO_LOCAL_DATE),
                this.to.format(DateTimeFormatter.ISO_LOCAL_DATE));
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)\n",
                this.done ? "X" : " ",
                this.name,
                this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy")),
                this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        Event event = (Event) obj;
        return this.name.equals(event.name)
                && this.done == event.done
                && this.from.equals(event.from)
                && this.to.equals(event.to);
    }
}
