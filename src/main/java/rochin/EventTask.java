package rochin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represent an Event task.
 */
class EventTask extends Task {

    protected LocalDateTime fromDateTime;
    protected LocalDateTime toDateTime;

    /**
     * Construct an EventTask with a description, starting, and ending date/time.
     *
     * @param description The description of the event task.
     * @param fromDateTime The starting datetime of the event.
     * @param toDateTime The ending datetime of the event.
     */
    public EventTask(String description, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        super(description);
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }

    /**
     * Return a string representation of the task type.
     *
     * @return A string representation of the task type.
     */
    @Override
    public String getTaskType() {
        return "E";
    }

    /**
     * Return a string representation of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + " (from: " + fromDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"))
                + " to: " + toDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }

    /**
     * Return a string representation of the task to be stored in the file.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toFileString() {
        return String.format(
                "%s | %d | %s | %s | %s",
                getTaskType(),
                isDone ? 1 : 0,
                description,
                DateAndTime.printDate(fromDateTime), DateAndTime.printDate(toDateTime));
    }
}
