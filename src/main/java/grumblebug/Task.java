package grumblebug;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that is added by user. Must be one of several different types.
 */
public abstract class Task {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");

    protected String description;
    protected boolean isDone;

    /**
     * To return a representation of doneness of task.
     * @return X or no X, for doneness.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " "; // mark done task with X
    }

    /**
     * Set the doneness state of a task.
     * @param doneness Boolean representing new state to set.
     */
    public void setDone(boolean doneness) {
        this.isDone = doneness;
    }

    /**
     * To return full status representing a task.
     * @return a String that is readable easily, showing task info.
     */
    public abstract String getFullStatus();

    public abstract char getTaskType();
}

