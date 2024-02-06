package tasks;

import datetime.DateTime;

/**
 * Represents a task with a deadline. A <code>tasks.DeadlineTask</code>
 * object contains its description or name, represented by a String,
 * a boolean indicating whether the task is marked, and
 * a String representing the date and/or time of deadline.
 */
public class DeadlineTask extends Task {
    public DateTime dateTime;

    /**
     * Basic constructor
     * @param taskName name of task to be tracked
     */
    public DeadlineTask(String taskName, String date_time) {
        super(taskName);
        this.dateTime = new DateTime(date_time);
        this.hasDate = true;
    }

    /**
     * Overloaded Constructor with date/time specified
     * and marked status
     * @param taskname name of task to be tracked
     * @param dateTime Date and/or Time of deadline
     * @param marked status of task (marked or unmarked)
     */
    public DeadlineTask(String taskname, Boolean marked, String dateTime) {
        super(taskname, marked);
        this.dateTime = new DateTime(dateTime);
    }

    @Override
    public Boolean isWithinDate(DateTime dt) {
        return this.dateTime.isSameDay(dt);
    }
    @Override
    public String stringForSaving() {
        return "D|" + super.stringForSaving() + "|" + this.dateTime;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() +
                String.format(" (by: %s)", this.dateTime);
    }
}
