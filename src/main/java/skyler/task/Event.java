package skyler.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import skyler.exception.SkylerException;

public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Constructs an Event task with the specified description, start date, end
     * date, and completion status.
     *
     * @param description The description of the event task.
     * @param from        The start date of the event.
     * @param to          The end date of the event.
     * @param isDone      The completion status of the task.
     * @throws SkylerException If there is an error creating the Event task.
     */
    public Event(String description, LocalDate from, LocalDate to, boolean isDone) throws SkylerException {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Retrieves the start date of the Event task.
     *
     * @return The start date of the event.
     */
    public LocalDate getFrom() {
        return from;
    }

    /**
     * Retrieves the end date of the Event task.
     *
     * @return The end date of the event.
     */
    public LocalDate getTo() {
        return to;
    }

    /**
     * Returns a string representation of the Event task, including its type,
     * description, start date, and end date.
     *
     * @return The formatted string representation of the Event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) +
                " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}