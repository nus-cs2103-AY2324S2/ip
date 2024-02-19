package chaterpillar.tasks;

import chaterpillar.datetime.DateTime;
import chaterpillar.exceptions.ChaterpillarException;

/**
 * Represents a task with a deadline. A <code>DeadlineTask</code>
 * object contains its description or name, represented by a String,
 * a boolean indicating whether the task is marked, and
 * a String representing the date and/or time of deadline.
 *
 * @author marclamp
 */
public class DeadlineTask extends Task {
    private DateTime dateTime;

    /**
     * Basic constructor
     *
     * @param taskName name of task to be tracked
     */
    public DeadlineTask(String taskName, String dateTime) throws ChaterpillarException {
        super(taskName);
        this.dateTime = new DateTime(dateTime);
        this.setHasDate();
    }

    /**
     * Overloaded Constructor with date/time specified
     * and marked status
     *
     * @param taskname name of task to be tracked
     * @param dateTime Date and/or Time of deadline
     * @param marked status of task (marked or unmarked)
     */
    public DeadlineTask(String taskname, Boolean marked, String dateTime) throws ChaterpillarException {
        super(taskname, marked);
        this.dateTime = new DateTime(dateTime);
        this.setHasDate();
    }

    @Override
    public void updateDate(String updatedDate) throws ChaterpillarException {
        if (!updatedDate.isBlank()) {
            this.dateTime = new DateTime(updatedDate);
        }
    }

    /**
     * Checks if the task falls on the same day as the date specified
     *
     * @param dt date specified
     * @return <code>true</code> if the task falls on the same day
     */
    @Override
    public boolean isWithinDate(DateTime dt) {
        return this.dateTime.isSameDay(dt);
    }
    @Override
    public String formatStringForSaving() {
        return "D|" + super.formatStringForSaving() + "|" + this.dateTime;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + String.format(" (by: %s)", this.dateTime);
    }
}
