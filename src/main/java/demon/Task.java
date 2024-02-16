package demon;

import java.time.LocalDateTime;

/**
 * This class is a parent class of Deadline, Event, and To-do classes. The class stores the description of a task
 * and its status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns whether the task is marked completed or not.
     *
     * @return A string representing completed ("X") or incomplete (" ")
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markDone() {
        isDone = true;
    }

    public void markNotDone() {
        isDone = false;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public LocalDateTime getDateTime() {
        return null;
    }

    public LocalDateTime getDateTimeFrom() {
        return null;
    }

    public LocalDateTime getDateTimeTo() {
        return null;
    }
}
