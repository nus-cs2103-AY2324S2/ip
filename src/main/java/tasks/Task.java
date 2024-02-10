package tasks;

/**
 * Represents a task with a description and a completion status.
 */
public abstract class Task {
    protected final String description;
    private boolean isDone;

    /**
     * Constructs a new task.Task with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description.trim();
        isDone = false;
    }

    public boolean hasQueryInDescription(String query) {
        return description.contains(query);
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Unmarks the task, setting it to not completed.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Getter for done class attribute.
     */
    public boolean isDone() {
        return isDone;
    }

    public abstract String convertTaskToFileString();

    /**
     * Returns a string representation of the task.
     * The string includes the task's completion status and its description.
     * The completion status is represented by '[X]' for a completed task and '[ ]' for an incomplete task.
     */
    @Override
    public String toString() {
        String checkBox = isDone ? "[X]" : "[ ]";
        return checkBox + " " + description;
    }
}
