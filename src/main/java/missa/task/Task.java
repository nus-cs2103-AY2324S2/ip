package missa.task;

/**
 * missa.task.Task class to store tasks for users.
 */
public class Task {
    private boolean isDone = false;
    private String task = null;

    public Task(String task) {
        this.task = task;
    }

    /*
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /*
     * Marks the task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns string representation of task data.
     *
     * @return String representation of task data.
     */
    public String getData() {
        int done = 0;
        if (isDone) {
            done = 1;
        }
        return " | " + done + " | " + task;
    }

    /**
     * Checks if this is a matching task.
     *
     * @param keyword String used to find matching tasks.
     * @return True if this task contains keyword.
     */
    public boolean checkKeyword(String keyword) {
        if (task.contains(keyword)) {
            return true;
        }
        return false;
    }

    /**
     * Returns string representation of task.
     * Indicates if the task is completed.
     *
     * @return String representation of task.
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.task;
        } else {
            return "[ ] " + this.task;
        }
    }
}
