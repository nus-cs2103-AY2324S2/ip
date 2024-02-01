/**
 * Represents a task with a deadline. A <code>DeadlineTask</code>
 * object contains its description or name, represented by a String,
 * a boolean indicating whether the task is marked, and
 * a String representing the date and/or time of deadline.
 */
public class DeadlineTask extends Task {
    public String date_time;

    /**
     * Basic constructor
     * @param taskname name of task to be tracked
     */
    public DeadlineTask(String taskname) {
        super(taskname);
        this.date_time = "";
    }
    /**
     * Overloaded Constructor with date/time specified
     * @param taskname name of task to be tracked
     * @param date_time Date and/or Time of deadline.
     */
    public DeadlineTask(String taskname, String date_time) {
        super(taskname);
        this.date_time = date_time;
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
        this.date_time = date_time;
    }
    @Override
    public String stringForSaving() {
        return "D|" + super.stringForSaving() + "|" + this.date_time;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() +
                String.format("(by: %s)", this.date_time);
    }
}
