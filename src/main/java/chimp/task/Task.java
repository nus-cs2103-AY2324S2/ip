package chimp.task;

/**
 * Represents a task with a text description and status.
 */
public class Task {
    private String text;
    private TaskStatus status;

    /**
     * Constructs a task with the given text and status.
     *
     * @param text   the text description of the task
     * @param status the status of the task
     */
    public Task(String text, TaskStatus status) {
        this.text = text;
        this.status = status;
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        this.status = TaskStatus.MARKED;
    }

    /**
     * Unmarks the task as completed.
     */
    public void unmark() {
        this.status = TaskStatus.UNMARKED;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        String mark = status == TaskStatus.MARKED ? "X" : " ";
        return "[" + mark + "] " + text;
    }
}
