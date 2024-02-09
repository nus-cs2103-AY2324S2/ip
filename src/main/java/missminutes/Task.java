package missminutes;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that can be marked or unmarked.
 * Implements Serializable to be stored in binary format files for persistence
 */
public class Task implements Serializable {
    protected static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy ha");
    protected String description;
    protected boolean isDone;


    /**
     * Creates a new task with the given `description`.
     * `isDone` is set to false initially.
     *
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves the status icon / string of the task. Used by toString
     *
     * @return The status icon ('X' if done, ' ' if not done)
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     *
     * @throws MissMinutesException If already marked as done, throws an exception
     */
    public void markAsDone() throws MissMinutesException {
        if (this.isDone) {
            throw new MissMinutesException("MissMinutes.Task already marked as done.");
        } else {
            this.isDone = true;
        }
    }

    /**
     * Unmarks the task
     *
     * @throws MissMinutesException If already marked as not done, throws an exception
     */
    public void unmark() throws MissMinutesException {
        if (this.isDone) {
            this.isDone = false;
        } else {
            throw new MissMinutesException("MissMinutes.Task already marked as undone.");
        }
    }

    /**
     * Returns the string representation of the task to be printed
     *
     * @return The string representation
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.description;
    }
}
