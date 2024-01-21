/**
 * Stores information regarding tasks to be recorded.
 *
 * @author KohGuanZeh
 */
public class Task {
    // Task description.
    private String description;
    // Task completion status.
    private boolean isDone;

    /**
     * Creates a new task with given description.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the task and its completion status.
     * Tasks that are done are marked with "[X]" whereas tasks that are not done are marked with "[ ]".
     *
     * @return Task completion status and description.
     */
    public String getTaskInformation() {
        String marker = this.isDone ? "[X]" : "[ ]";
        return marker + " " + this.description;
    }

    /**
     * Sets task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets task as not done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }
}
