/**
 * Task with a deadline attached.
 *
 * @author KohGuanZeh
 */
public class Deadline extends Task {
    // Deadline of task.
    private String deadline;

    /**
     * Creates a Task with given description and specified deadline.
     *
     * @param description Description of task.
     * @param deadline Deadline for the task.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Returns the task, task type, completion status and task deadline.
     * Tasks of Event type are marked with [D].
     * Tasks that are done are marked with "[X]" whereas tasks that are not done are marked with "[ ]".
     *
     * @return Task type, completion status, description and deadline.
     */
    @Override
    public String getTaskInformation() {
        return "[D]" + super.getTaskInformation() + " (by: " + this.deadline + ")";
    }

}
