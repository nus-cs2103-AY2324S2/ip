package talkingbot.task;

import java.time.LocalDateTime;

import talkingbot.type.TaskType;

/**
 * Class for the Event task.
 */
public class Event extends Task {
    private static final String EVENT_SAVE_FILE_FORMAT = "E | %d | %s | %s | %s";
    private static final String EVENT_DISPLAY_FORMAT = "[E] %s (from: %s to: %s)";
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    /**
     * Creates a new Event instance.
     *
     * @param description Description of the task.
     * @param isDone Marks whether the task has been done.
     * @param startTime Event starting time.
     * @param endTime Event ending time.
     */
    public Event(String description, boolean isDone, String startTime, String endTime) {
        super(description, isDone, TaskType.EVENT);
        this.startTime = LocalDateTime.parse(startTime, DATE_TIME_ENTRY_FORMAT);
        this.endTime = LocalDateTime.parse(endTime, DATE_TIME_ENTRY_FORMAT);
    }

    /**
     * Creates a new Event instance with isDone equal to false.
     *
     * @param description Description of the task.
     * @param startTime Event starting time.
     * @param endTime Event ending time.
     */
    public Event(String description, String startTime, String endTime) {
        this(description, false, startTime, endTime);
    }


    /**
     * Returns the starting time of the Event.
     *
     * @return Event starting time.
     */
    private LocalDateTime getStartTime() {
        return this.startTime;
    }

    /**
     * Returns the ending time of the Event.
     *
     * @return Event ending time.
     */
    private LocalDateTime getEndTime() {
        return this.endTime;
    }

    /**
     * Returns the String describing this task that will be saved into the save file.
     *
     * @return A formatted String.
     */
    @Override
    public String getSaveFileString() {
        return String.format(EVENT_SAVE_FILE_FORMAT, super.getDoneAsInt(),
                super.getDescription(), this.getStartTime().format(DATE_TIME_ENTRY_FORMAT),
                this.getEndTime().format(DATE_TIME_ENTRY_FORMAT));
    }

    /**
     * Returns how the Event object will be printed as a String.
     *
     * @return A formatted String.
     */
    @Override
    public String toString() {
        return String.format(EVENT_DISPLAY_FORMAT, super.toString(),
                this.getStartTime().format(DATE_TIME_OUT_FORMAT),
                this.getEndTime().format(DATE_TIME_OUT_FORMAT));
    }
}
