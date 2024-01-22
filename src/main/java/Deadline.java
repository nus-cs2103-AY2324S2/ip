public class Deadline extends Task{
    /**
     * Date at which the deadline expires
     */
    private String date;

    /**
     *
     * @param description: Description of task.
     * @param date: Date at which the deadline expires.
     */
    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        String taskType = "[D]";
        String deadlineString = taskType + super.toString() + " (by: " + date + ")";
        return deadlineString;
    }
}
