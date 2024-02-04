package task;

/**
 * Represents an Event task.
 * An <code>Event</code> object corresponds to a task with a description and a
 * start date and end date.
 * e.g., <code>Event project meeting /from 2020-02-02 to 2020-02-03</code>
 */
public class Event extends Task {
    public static final String TYPE = "E";
    private final java.time.LocalDate startDate;
    private final java.time.LocalDate endDate;

    /**
     * Constructs an Event object with the specified description, start date, and
     * end date.
     *
     * @param description The description of the event.
     * @param startDate   The start date of the event.
     * @param endDate     The end date of the event.
     */
    public Event(int taskID, String description, java.time.LocalDate startDate, java.time.LocalDate endDate) {
        super(taskID, description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Constructs an Event object with the specified description, start date, end
     * date, and status.
     *
     * @param description The description of the event.
     * @param startDate   The start date of the event.
     * @param endDate     The end date of the event.
     * @param isDone      The status of the event.
     */
    public Event(int taskID, String description, java.time.LocalDate startDate, java.time.LocalDate endDate,
            boolean isDone) {
        super(taskID, description, isDone);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (from: %s to: %s)", TYPE, super.toString(), this.startDate, this.endDate);
    }
}
