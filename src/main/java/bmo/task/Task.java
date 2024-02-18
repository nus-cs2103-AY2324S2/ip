package bmo.task;

/**
 * Represents a task.
 */
public class Task {
    protected String task;
    protected Boolean isDone;

    public Task(String task) {
        this.task = task;
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

    @Override
    public String toString() {
        return this.task;
    }
}
