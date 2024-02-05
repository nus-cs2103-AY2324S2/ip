/**
 * Represents a task with a deadline. A <code>DeadlineTask</code>
 * object contains its description or name, represented by a String,
 * a boolean indicating whether the task is marked, and
 * a String representing the date and/or time of deadline.
 */
public class DeadlineTask extends Task {
    public String dateTime;

    /**
     * Basic constructor
     * @param taskName name of task to be tracked
     */
    public DeadlineTask(String taskName, String dateTime) {
        super(taskName);
        this.dateTime = dateTime;
    }
    /**
     * Overloaded Constructor with date/time specified
     * and marked status
     * @param taskname name of task to be tracked
     * @param date_time Date and/or Time of deadline
     * @param marked status of task (marked or unmarked)
     */
    public DeadlineTask(String taskname, Boolean marked, String date_time) {
        super(taskname, marked);
        this.dateTime = date_time;
    }
    @Override
    public String stringForSaving() {
        return "D|" + super.stringForSaving() + "|" + this.dateTime;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() +
                String.format("(by: %s)", this.dateTime);
    }
}
