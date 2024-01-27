package talkingbot.task;

import java.time.LocalDateTime;

import talkingbot.type.TaskType;

/**
 * A class that represents the Event task.
 */
public class Event extends Task {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    /**
     * Constructor for the Event class.
     *
     * @param description Description of the task.
     * @param isDone Marks whether the task has been done.
     * @param startTime Event starting time.
     * @param endTime Event ending time.
     */
    public Event(String description, boolean isDone, String startTime, String endTime) {
        super(description, isDone, TaskType.EVENT);
        this.startTime = LocalDateTime.parse(startTime, super.dateTimeEntryForm);
        this.endTime = LocalDateTime.parse(endTime, super.dateTimeEntryForm);
    }


    /**
     * Returns the starting time of the Event.
     *
     * @return Event starting time.
     */
    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    /**
     * Returns the ending time of the Event.
     *
     * @return Event ending time.
     */
    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    /**
     * Returns the String describing this task that will be saved into the save file.
     *
     * @return A formatted String.
     */
    @Override
    public String getSaveFileString() {
        return String.format("E | %d | %s | %s | %s", super.getDoneAsInt(),
                super.getDescription(), this.getStartTime().format(super.dateTimeEntryForm),
                this.getEndTime().format(dateTimeEntryForm));
    }

    /**
     * Returns how the Event object will be printed as a String.
     *
     * @return A formatted String.
     */
    @Override
    public String toString() {
        return String.format("[E] %s (from: %s to: %s)", super.toString(),
                this.getStartTime().format(dateTimeOutForm),
                this.getEndTime().format(dateTimeOutForm));
    }
}
