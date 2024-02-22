package osiris.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The EventTask class represents a task that occurs within a specific time frame.
 * It extends the Task class.
 */
public class EventTask extends Task {

    /** Starting Date Time of Task. **/
    private final LocalDateTime startDateTime;

    /** Ending Date Time of Task. **/
    private final LocalDateTime endDateTime;

    /**
     * Constructs an EventTask object with the given task name, start date/time, and end date/time.
     *
     * @param taskName      The name of the task.
     * @param startDateTime The start date and time of the task.
     * @param endDateTime   The end date and time of the task.
     */
    public EventTask(String taskName, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(taskName);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Constructs an EventTask object with the given task name, completion status, start date/time, and end date/time.
     *
     * @param taskName      The name of the task.
     * @param isCompleted   The completion status of the task.
     * @param startDateTime The start date and time of the task.
     * @param endDateTime   The end date and time of the task.
     */
    public EventTask(String taskName, boolean isCompleted, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(taskName, isCompleted);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Retrieves the string representation of the start date and time of the task.
     *
     * @return The start date and time of the task formatted as "MMM d yyyy h:mm a".
     */
    public String getStartDateTimeStr() {
        return startDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a"));
    }

    /**
     * Retrieves the string representation of the end date and time of the task.
     *
     * @return The end date and time of the task formatted as "MMM d yyyy h:mm a".
     */
    public String getEndDateTimeStr() {
        return endDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a"));
    }

    /**
     * Returns start Local Date Time of Task
     *
     * @return LocalDateTime    The start time of the task.
     */
    @Override
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    /**
     * Retrieves the string representation of the EventTask for storage purposes.
     *
     * @return The string representation of the EventTask.
     */
    @Override
    public String getStringStorageRepresentation() {
        return String.format("E | %s | %s | %s", super.getStringStorageRepresentation(),
                this.getStartDateTimeStr(), this.getEndDateTimeStr());
    }

    /**
     * Retrieves the string representation of the EventTask for display purposes.
     *
     * @return The string representation of the EventTask.
     */
    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)", super.toString(),
                this.getStartDateTimeStr(), this.getEndDateTimeStr());
    }
}
