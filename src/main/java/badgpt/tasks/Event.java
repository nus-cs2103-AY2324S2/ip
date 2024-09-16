package badgpt.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Representation of an event which lasts for a period of time.
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Creates a new Event object.
     *
     * @param description The description of the event (e.g. what is happening).
     * @param from The date the event starts.
     * @param to The date the event ends.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String saveTask() {
        return "[E]" + super.toString() + " from: " + from + " to: " + to;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isHappening(LocalDate date) {
        boolean isAfter = to.isAfter(date) || to.isEqual(date);
        boolean isBefore = from.isBefore(date) || from.isEqual(date);
        return isAfter && isBefore;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " +
                from.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + " to: " +
                to.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }
}
