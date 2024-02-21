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
     * Constructs an EventTask with a description, starting, and ending date/time.
     *
     * @param description The description of the event task.
     * @param fromDateTime        The starting date/time of the event.
     * @param toDateTime          The ending date/time of the event.
     */
    public EventTask(String description, LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        super(description);
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
    }

    /**
     * Return a new event task.
     *
     * @param descriptionWithDateTime description with date.
     * @return A new event task.
     */
    public EventTask createTask(String descriptionWithDateTime) throws RochinException {
        String[] parts = descriptionWithDateTime.split("/from");
        if (parts.length == 2) {
            String description = parts[0].trim();
            String[] dateTimeRange = parts[1].split("/to");
            if (dateTimeRange.length == 2) {
                String fromDateTimeString = dateTimeRange[0].trim();
                String toDateTimeString = dateTimeRange[1].trim();
                LocalDateTime fromDateTime = DateAndTime.parseDateTime(fromDateTimeString);
                LocalDateTime toDateTime = DateAndTime.parseDateTime(toDateTimeString);
                return new EventTask(description, fromDateTime, toDateTime);
            }
        }
        throw new RochinException("OOPS!!! Please provide a description, start time, and end time for an event task.");
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
        return "[E]" + super.toString() + " (from: " + fromDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) +
                " to: " + toDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }

    @Override
    public String toFileString() {
        return String.format("%s | %d | %s | %s to %s", getTaskType(), isDone ? 1 : 0, description,
                DateAndTime.printDate(fromDateTime), DateAndTime.printDate(toDateTime));
    }
}
