package me.ruibin.leto.tasklist;

/** Class representing a task being keep tracked of by Leto. */
public class Task {
    private boolean completed;
    private String message;

    /**
     * Constructor for task allowing the specification on whether it is completed
     * and the description.
     *
     * @param completed Whether the task is completed.
     * @param message Description of the task.
     */
    public Task(Boolean completed, String message) {
        this.completed = completed;
        this.message = message;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markCompleted() {
        this.completed = true;
    }

    public void markUncompleted() {
        this.completed = false;
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

