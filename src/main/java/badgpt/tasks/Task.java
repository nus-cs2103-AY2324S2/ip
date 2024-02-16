package badgpt.tasks;

import java.time.LocalDate;

/**
 * Representation of a task object. The task can have a description and can be completed.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new Task object. It is not done by default.
     *
     * @param description The description of the task (e.g. what must be done).
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns the status of the task.
     *
     * @return [X] if it is complete. Otherwise, [ ] is returned.
     */
    public String getStatusIcon() {
        return "[" + (isDone ? "X" : " ") + "]"; // mark done task with X
    }

    /**
     * Gets the status of the task.
     */
    public boolean isComplete() {
        return isDone;
    }

    /**
     * Changes the status of the task to complete.
     */
    public void complete() {
        isDone = true;
    }

    /**
     * Changes the status of the task to incomplete.
     */
    public void uncomplete() {
        isDone = false;
    }

    /**
     * Returns a representation of the task to be saved in the text file.
     */
    public String saveTask() {
        return this.toString();
    }

    /**
     * Checks if this task occurs before or during a given date.
     *
     * @param date The date to check.
     * @return true if the task does occur before or during the date, false otherwise.
     */
    public boolean isHappening(LocalDate date) {
        return false;
    }

    /**
     * Returns a string representation of the task.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
