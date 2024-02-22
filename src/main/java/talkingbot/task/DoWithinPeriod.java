package talkingbot.task;

import java.time.LocalDateTime;

import talkingbot.type.TaskType;

/**
 * Class for the DoWithinPeriod task.
 */
public class DoWithinPeriod extends Task {
    private static final String DO_WITHIN_PERIOD_SAVE_FILE_FORMAT = "W | %d | %s | %s | %s";
    private static final String DO_WITHIN_PERIOD_DISPLAY_FORMAT = "[W] %s (between %s and %s)";
    private final LocalDateTime beginTime;
    private final LocalDateTime endTime;

    /**
     * Creates a new DoWithinPeriod instance.
     *
     * @param description Description of the task.
     * @param isDone Marks whether the task has been done.
     * @param beginTime Marks the beginning time of the task.
     * @param endTime Marks the ending time of the task.
     */
    public DoWithinPeriod(String description, boolean isDone, String beginTime, String endTime) {
        super(description, isDone, TaskType.DO_WITHIN_PERIOD);
        this.beginTime = LocalDateTime.parse(beginTime, DATE_TIME_ENTRY_FORMAT);
        this.endTime = LocalDateTime.parse(endTime, DATE_TIME_ENTRY_FORMAT);
    }

    /**
     * Creates a new DoWithinPeriod instance with isDone equal to false.
     *
     * @param description Description of the task.
     * @param beginTime Marks the beginning time of the task.
     * @param endTime Marks the ending time of the task.
     */
    public DoWithinPeriod(String description, String beginTime, String endTime) {
        this(description, false, beginTime, endTime);
    }

    private LocalDateTime getBeginTime() {
        return this.beginTime;
    }

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
        return String.format(DO_WITHIN_PERIOD_SAVE_FILE_FORMAT,
                super.getDoneAsInt(), super.getDescription(),
                this.getBeginTime().format(DATE_TIME_ENTRY_FORMAT),
                this.getEndTime().format(DATE_TIME_ENTRY_FORMAT));
    }

    /**
     * Returns how the DoWithinPeriod object will be printed as a String.
     *
     * @return A formatted String.
     */
    @Override
    public String toString() {
        return String.format(DO_WITHIN_PERIOD_DISPLAY_FORMAT,
                super.toString(), this.getBeginTime().format(DATE_TIME_OUT_FORMAT),
                this.getEndTime().format(DATE_TIME_OUT_FORMAT));
    }
}
