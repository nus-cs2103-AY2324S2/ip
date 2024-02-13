package paimon.task;

import java.time.LocalDateTime;

/**
 * Represents an event task that has both a start and end date/time. This class extends {@link Task}
 * to incorporate the timeframe functionality, making it suitable for events or appointments.
 */
public class EventTask extends Task {
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    /**
     * Constructs an EventTask with a description, start date/time, and end date/time.
     *
     * @param description The description of the event task.
     * @param startDate   The start date and time of the event, represented as a {@link LocalDateTime}.
     * @param endDate     The end date and time of the event, represented as a {@link LocalDateTime}.
     */
    public EventTask(String description, LocalDateTime startDate, LocalDateTime endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;

    }

    /**
     * Returns the string representation of the event task, including its status (done or not done),
     * description, and the timeframe (start and end dates/times).
     *
     * @return A string indicating the task's type (E for Event), status, description, and timeframe.
     */
    @Override
    public String getTask() {
        if (isDone) {
            return "[E][X] " + this.description + " (from: " + startDate.format(DATE_FORMAT) + ", to: " + this.endDate.format(DATE_FORMAT) + ")";
        } else {
            return "[E][ ] " + this.description + " (from: " + startDate.format(DATE_FORMAT) + ", to: " + this.endDate.format(DATE_FORMAT) + ")";
        }
    }

    /**
     * Returns the string representation of the event task for saving to a file. This includes
     * the task type (E), its status (done or not done), description, and start and end dates/times
     * formatted according to {@link Task#DATE_FORMAT}.
     *
     * @return A string suitable for file storage that captures all relevant details of the event task.
     */
    @Override
    public String toFileString() {
        if (this.isDone) {
            return "E | 1 | " + this.description + " | " + this.startDate.format(DATE_FORMAT) + " | " + this.endDate.format(DATE_FORMAT);
        } else {
            return "E | 0 | " + this.description + " | " + this.startDate.format(DATE_FORMAT) + " | " + this.endDate.format(DATE_FORMAT);
        }
    }
}
