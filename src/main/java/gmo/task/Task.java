package gmo.task;

/**
 * Represents a task.
 */
public class Task implements Comparable<Task> {
    protected String description;
    protected Boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return String representation of the status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public Boolean getStatus() {
        return this.isDone;
    }

    public void setStatus(Boolean b) {
        this.isDone = b;
    }

    /**
     * Returns the formatted string representation of the task to be saved in the file.
     *
     * @return String representation of the task to be saved in the file.
     */
    public String toSaveData() {
        String done = this.getStatus() ? "1" : "0";
        return "T | " + done + " | " + super.toString() + "\n";
    }

    /**
     * Returns the type priority of the task.
     *
     * @return Integer representing the type priority of the task.
     */
    public int getTypePriority() { return Integer.MAX_VALUE; }

    /**
     * Returns the comparison of the description of the tasks, used for sorting.
     *
     * @return Integer representing the comparison of the description of the tasks.
     */
    public int compareTo(Task t) {
        return this.description.compareTo(t.description);
    };

    @Override
    public String toString() {
        return this.description;
    }
}
