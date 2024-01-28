package duke.task;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task implements Serializable {
    protected String msg;
    protected boolean done;

    public Task() {
        this.done = false;
    }
    public void mark() {
        this.done = true;
    }
    public void unmark() {
        this.done = false;
    }
    public abstract String toString();

    protected String dateFormat(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Checks if the task contains a keyword.
     * @param keyword The keyword to check for.
     * @return true if contains, false if it does not.
     */
    public boolean contains(String keyword) {
        return this.msg.contains(keyword);
    }
}