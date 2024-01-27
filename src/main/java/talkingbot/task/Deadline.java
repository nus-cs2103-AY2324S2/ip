package talkingbot.task;

import java.time.LocalDateTime;
import talkingbot.type.TaskType;

/**
 * A class that represents the Deadline task.
 */
public class Deadline extends Task {
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
        this.endTime = LocalDateTime.parse(endTime, super.dateTimeEntryForm);
    }

    /**
     * Returns the ending time of the Deadline.
     *
     * @return Deadline ending time.
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
        return String.format("D | %d | %s | %s", super.getDoneAsInt(),
                super.getDescription(), this.getEndTime().format(super.dateTimeEntryForm));
    }

    /**
     * Returns how the Deadline object will be printed as a String.
     *
     * @return A formatted String.
     */
    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(),
                this.getEndTime().format(super.dateTimeOutForm));
    }
}
