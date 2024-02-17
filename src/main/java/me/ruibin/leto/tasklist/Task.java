package me.ruibin.leto.tasklist;

/** Class representing a task being keep tracked of by Leto. */
public class Task {
    private boolean isCompleted;
    private String message;

    /**
     * Constructor for task allowing the specification on whether it is completed
     * and the description.
     *
     * @param isCompleted Whether the task is completed.
     * @param message Description of the task.
     */
    public Task(Boolean isCompleted, String message) {
        this.isCompleted = isCompleted;
        this.message = message;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void markCompleted() {
        this.isCompleted = true;
    }

    public void markUncompleted() {
        this.isCompleted = false;
    }

    @Override
    public String toString() {
        return "[" + (isCompleted() ? "X" : " ") + "] " + this.message;
    }

    /**
     * Returns the object as a string formatted as a row in a csv table according to format
     * Completed,Task
     *
     * @return String in csv format
     */
    public String toCsvString() {
        return (isCompleted() ? "Y" : "N") + "," + this.message;
    }
}

