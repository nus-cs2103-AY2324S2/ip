package duke;

import java.time.LocalDate;

public class Event extends Task{

    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructs an Event with a description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     * @throws DukeException If the date strings are not in a valid format.
     */
    public Event(String description, String from, String to) throws DukeException {
        super(description);
        this.from = DateChecker.parseDate(from);
        this.to = DateChecker.parseDate(to);
    }

    /**
     * Returns the description of the saved task.
     *
     * @return The description of the saved task.
     */
    @Override
    public String saveData() {
        return "E | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | " + this.from + " | " + this.to;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DateChecker.formatDate(this.from) + " to: " + DateChecker.formatDate(this.to) + ")";
    }
}
