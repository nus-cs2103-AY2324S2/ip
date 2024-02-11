package duke.task;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Base class for all tasks.
 * Represents common attributes shared between all of them.
 */
public abstract class Task implements Serializable {
    protected String event;
    protected boolean done;

    public Task() {
        this.done = false;
    }

    /**
     * Mark a task as done.
     */
    public void mark() {
        this.done = true;
    }

    /**
     * Mark as task as not done.
     */
    public void unmark() {
        this.done = false;
    }

    public abstract String toString();

    /**
     * Formats a date in the format Month day Year.
     *
     * @param date The date to format to.
     * @return String of the formatted date.
     */

    protected String dateFormat(LocalDateTime date) {
        assert date != null;
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Checks if the task contains a keyword.
     *
     * @param keyword The keyword to check for.
     * @return true if contains, false if it does not.
     */
    public boolean contains(String keyword) {
        assert keyword != null;
        return this.event.contains(keyword);
    }
}
