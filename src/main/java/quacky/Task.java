package quacky;

/**
 * Represents an abstract task in the Quacky application. This class provides
 * the common structure for tasks, including their description and completion status.
 */
public abstract class Task {
    protected final String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified description. By default, the task is not completed.
     *
     * @param description The textual description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task. An "X" represents a completed task,
     * while a space (" ") indicates an incomplete task.
     *
     * @return A string representing the completion status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * This method returns a value of the status for a given task
     *
     * @return 0 if the task is done and 1 otherwise
     */
    public int getStatusValue() {
        return (isDone ? 0 : 1);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), description);
    }

    /**
     * Marks the task as completed by setting its completion status to {@code true}.
     */
    public void markDone() {
        isDone = true;
    }

    public void unmarkDone() {
        isDone = false;
    }

    /**
     * This method is used to return a string that is easy to parse to recover the tasks data
     *
     * @return the parsible string representation of the task
     */
    protected String toFileString() {
        return getStatusValue() + " | " + this.description;
    }

    /**
     * Checks if this task clashes with another task.
     * The base method returns false, indicating no clash for basic tasks.
     *
     * @param other The task to compare against.
     * @return true if there is a clash, false otherwise.
     */
    public boolean clashesWith(Task other) {
        return false; // By default, tasks do not clash.
    }

}