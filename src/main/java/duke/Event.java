package duke;

import java.time.LocalDate;

/**
 * Constructs an event task.
 */
public class Event extends Task {

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
     * Returns the start time of the event.
     *
     * @return The start time of the event.
     */
    public LocalDate getStart() {
        return this.from;
    }

    /**
     * Returns the end time of the event.
     *
     * @return The end time of the event.
     */
    public LocalDate getEnd() {
        return this.to;
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
        return "[E]" + super.toString() + " (from: " + DateChecker.formatDate(this.from)
                + " to: " + DateChecker.formatDate(this.to) + ")";
    }

    /**
     * Returns whether the task is equal to another object.
     *
     * @param obj The object to be compared.
     * @return Whether the task is equal to the object.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Event event = (Event) obj;
        return this.getDescription().equals(event.getDescription()) &&
                this.getStart().equals(event.getStart()) &&
                this.getEnd().equals(event.getEnd());
    }

}
