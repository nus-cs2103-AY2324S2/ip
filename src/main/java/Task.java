/**
 * Represents a task with a description and a completion status.
 */
public class Task {
    private final String description;
    private boolean done;

    /**
     * Constructs a new Task with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        this.done = true;
    }

    /**
     * Unmarks the task, setting it to not completed.
     */
    public void unmark() {
        this.done = false;
    }

    /**
     * Returns a string representation of the Task.
     * The string includes the task's completion status and its description.
     * The completion status is represented by '[X]' for a completed task and '[ ]' for an incomplete task.
     */
    @Override
    public String toString() {
        String checkBox = this.done ? "[X]" : "[ ]";
        return checkBox + " " + this.description;
    }
}
