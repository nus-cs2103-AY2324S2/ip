/**
 * Deadline is a task with a specified date set as the deadline.
 */
public class Deadline extends Task{
    /** Date at which the deadline expires */
    private String date;

    /**
     * Constructor of deadline
     *
     * @param description: Description of task.
     * @param date: Date at which the deadline expires.
     */
    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    /**
     * Getter for date.
     *
     * @return The deadline.
     */
    public String getDate() {
        return this.date;
    }

    @Override
    public String toFileString() {
        String taskType = "D";
        String isDone = this.getStatus() ? "1" : "0";
        String description = super.toFileString();
        String deadline = this.getDate();
        return taskType + " | " + isDone + " | " + description + " | " + deadline;
    }

    @Override
    public String toString() {
        String taskType = "[D]";
        String deadlineString = taskType + super.toString() + " (by: " + date + ")";
        return deadlineString;
    }
}
