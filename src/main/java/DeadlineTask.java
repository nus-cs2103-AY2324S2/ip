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
     * @param taskName name of task to be tracked
     */
    public DeadlineTask(String taskName, String date_time) {
        super(taskName);
        this.date_time = date_time;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() +
                String.format("(by: %s)", this.date_time);
    }
}
