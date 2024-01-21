/**
 * Task with no time limit attached.
 *
 * @author KohGuanZeh
 */
public class ToDo extends Task {
    /**
     * Creates a new task with no time limit attached.
     *
     * @param description Description of task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the task, task type and its completion status.
     * Tasks of ToDo type are marked with [T].
     * Tasks that are done are marked with "[X]" whereas tasks that are not done are marked with "[ ]".
     *
     * @return Task type, completion status and description.
     */
    @Override
    public String getTaskInformation() {
        return "[T]" + super.getTaskInformation();
    }
}
