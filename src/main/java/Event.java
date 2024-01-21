/**
 * Task with a from and to date and time.
 *
 * @author KohGuanZeh
 */
public class Event extends Task {
    // Start datetime.
    private String from;
    // End datetime.
    private String to;

    /**
     * Creates a task with given description and specified duration.
     * Duration includes both date and time.
     *
     * @param description Description of task.
     * @param from Start datetime of event.
     * @param to End datetime of event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the task, task type, completion status and event duration.
     * Tasks of Event type are marked with [E].
     * Tasks that are done are marked with "[X]" whereas tasks that are not done are marked with "[ ]".
     *
     * @return Task type, completion status, description and duration.
     */
    @Override
    public String getTaskInformation() {
        return "[E]" + super.getTaskInformation() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
