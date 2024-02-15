package talkingbot.task;

import java.time.LocalDateTime;

import talkingbot.type.TaskType;

/**
 * A class that represents the Deadline task.
 */
public class Deadline extends Task {
    private static final String DEADLINE_SAVE_FILE_FORMAT = "D | %d | %s | %s";
    private static final String DEADLINE_DISPLAY_FORMAT = "[D] %s (by: %s)";
    private final LocalDateTime endTime;

    /**
     * A constructor for the Deadline class.
     *
     * @param description Description of the task.
     * @param isDone Marks whether the task has been done.
     * @param endTime Marks the ending time of the deadline.
     */
    public Deadline(String description, boolean isDone, String endTime) {
        super(description, isDone, TaskType.DEADLINE);
        this.endTime = LocalDateTime.parse(endTime, DATE_TIME_ENTRY_FORMAT);
    }

    /**
     * Returns the ending time of the Deadline.
     *
     * @return Deadline ending time.
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
        return String.format(DEADLINE_SAVE_FILE_FORMAT, super.getDoneAsInt(),
                super.getDescription(),
                this.getEndTime().format(DATE_TIME_ENTRY_FORMAT));
    }

    /**
     * Returns how the Deadline object will be printed as a String.
     *
     * @return A formatted String.
     */
    @Override
    public String toString() {
        return String.format(DEADLINE_DISPLAY_FORMAT, super.toString(),
                this.getEndTime().format(DATE_TIME_OUT_FORMAT));
    }
}
