package guanguan;

import java.time.LocalDate;

/**
 * Represents an Event class inherited from Task.
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructor for an Event.
     *
     * @param description description of the event
     * @param from        starting date of the event
     * @param to          ending date of the event
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String saveToText() {
        return String.format("E | %s | %s | %s | %s", this.isDone ? 1 : 0, this.description,
                Utils.convertDateTimeToString(this.from), Utils.convertDateTimeToString(this.to));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), Utils.printTime(this.from),
                Utils.printTime(this.to));
    }
}
