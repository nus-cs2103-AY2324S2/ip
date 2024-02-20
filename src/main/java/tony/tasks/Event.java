package tony.tasks;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class represents a task that spans a specific time period.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Creates a new Event task with the specified description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.type = TaskType.EVENT;
        this.from = from;
        this.to = to;

    }
    /**
     * Returns a string representation of the Event task for display purposes.
     *
     * @return A formatted string representing the Event task with start and end times.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy hh mm"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy hh mm")) + ")";
    }

    /**
     * Returns a formatted string representation of the Event task for storage purposes.
     *
     * @return A formatted string representing the Event task with start and end times.
     */
    @Override
    public String formattedString() {
        return "E" + super.formattedString() + "|" + from.toString() + "|" + to.toString();
    }

    /**
     * Returns the type of the task as a string.
     *
     * @return The type of the task ("EVENT").
     */
    @Override
    public String getType() {
        return type.toString();
    }

    public void setFromDate(LocalDateTime date) {
        this.from = date;
    }

    public void setToDate(LocalDateTime date) {
        this.to = date;
    }
}
