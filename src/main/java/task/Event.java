package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * {@link Task} that contains a {@link java.time.LocalDate} start date and a {@link java.time.LocalDate} end date.
 */
public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    /**
     * Initialises an Event task with a description, a start and end date.
     * Is marked as not completed by default.
     *
     * @param desc Description of the event to be completed.
     * @param from Date of when the event starts.
     * @param to Date of when the event ends.
     */
    public Event(String desc, LocalDate from, LocalDate to) {
        super(desc);
        this.from = from;
        this.to = to;
    }

    /**
     * Initialises an Event task with the task completion status, a description,start and end date.
     * Use to manually set the completion status of the task when initialising.
     *
     * @param isDone Completion status of the task.
     * @param desc Description of the event.
     * @param from Date of when the event starts.
     * @param to Date of when the event ends.
     */
    public Event(boolean isDone, String desc, LocalDate from, LocalDate to) {
        super(isDone, desc);
        this.from = from;
        this.to = to;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s, to: %s)",
                getStatusIcon(),
                getDescription(),
                from.format(DateTimeFormatter.ofPattern("d MMM yyyy")),
                to.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
    }
}
