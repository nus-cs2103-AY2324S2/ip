/**
 * TimeBlock is a class that extends Task. It represents a task that has a
 * specific time block
 */
public class TimeBlock extends Task {
    // The start time of the task.
    protected String fromTime;
    // The end time of the task.
    protected String toTime;

    /**
     * Constructs a new TimeBlock object
     *
     * @param description The description of the task
     * @param fromTime    The start time of the task
     * @param toTime      The end time of the task
     */
    public TimeBlock(String description, String fromTime, String toTime) {
        super(description);
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    /**
     * Returns a string representation of the TimeBlock object
     *
     * @return A string representation of the TimeBlock object, including the task
     *         type (E), the task description,
     *         the start time, and the end time
     */
    @Override
    public String toString() {
        return "E" + super.toString() + " | from: " + this.fromTime + " to: " + this.toTime + "";
    }
}