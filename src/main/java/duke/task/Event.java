package duke.task;

import java.time.LocalDate;

import duke.exception.DukeException;

/**
 * Represents an event task.
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructor for Event.
     *
     * @param description The description of the event task.
     * @param from The start date of the event task.
     * @param to The end date of the event task.
     */
    public Event(String description, LocalDate from, LocalDate to, boolean isDone) throws DukeException {
        super(description);
        this.from = from;
        this.to = to;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + " to: " + to + ")";
    }

    @Override
    public String getDataString() {
        return "E | " + (isDone ? "1" : "0") + " | " + super.getDescription() + " | " + from + " | " + to;
    }
}
