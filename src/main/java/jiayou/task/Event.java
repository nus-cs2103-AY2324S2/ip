package jiayou.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents event, a subtype of the task, which has both a starting date and an ending date.
 * @author Liu Jiayao
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Returns a new event task with the given description.
     *
     * @param description the description of the event task.
     * @param from the start of the event.
     * @param to the end of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    public LocalDate getFrom() {
        return this.from;
    }

    public LocalDate getTo() {
        return this.to;
    }

    public void setFrom(String newFrom) {
        this.from = LocalDate.parse(newFrom);
    }

    public void setTo(String newTo) {
        this.to = LocalDate.parse(newTo);
    }

    @Override
    public String toStringForStore() {
        return "E" + super.toStringForStore() + " | from " + this.from.toString() + " to " + this.to.toString();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
