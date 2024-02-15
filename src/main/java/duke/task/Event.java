package duke.task;

import java.time.LocalDate;

/**
 * Represents a task with an event in the Duke application.
 */
public class Event extends Task {

    /**
     * The starting date of the event.
     */
    protected LocalDate from;

    /**
     * The ending date of the event.
     */
    protected LocalDate to;

    /**
     * Constructs an Event task with the specified description, starting date, and ending date.
     *
     * @param description The description of the event.
     * @param from The starting date of the event.
     * @param to The ending date of the event.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the type code representing the task type.
     *
     * @return A string representing the task type code.
     */
    public String getType() {
        return "E";
    }

    /**
     * Gets the starting date of the event.
     *
     * @return The starting date.
     */
    public LocalDate getFrom() {
        return this.from;
    }

    /**
     * Gets the ending date of the event.
     *
     * @return The ending date.
     */
    public LocalDate getTo() {
        return this.to;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            Event event = (Event) obj;
            return this.getDescription().equals(event.getDescription()) && this.getTo().equals(event.getTo())
                    && this.getFrom().equals(event.getFrom());
        }
        return false;
    }

    /**
     * Converts the task to a string representation.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.getDayOfMonth()
                + " " + from.getMonth() + " " + from.getYear() + " to: " + to.getDayOfMonth()
                + " " + to.getMonth() + " " + to.getYear() + ")";
    }
}
