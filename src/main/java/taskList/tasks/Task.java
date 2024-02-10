package tasklist.tasks;

import java.io.Serializable;

/** Represents a Task that needs to be completed. */
public class Task implements Serializable {
    // the task that needs to be completed.
    protected String item;
    // an indicator of whether the task is completed or not.
    protected boolean isDone;

    /**
     * Initialize the Task.
     *
     * @param item the task that needs to be completed.
     */
    public Task(String item) {
        this.item = item;
        this.isDone = false;
    }

    // retrieve task status
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    // mark the item as completed.
    public void markItem() {
        this.isDone = true;
    }

    // unmark the item as not completed.
    public void unmarkItem() {
        this.isDone = false;
    }

    /** Find list of item based on input by matching the keywords */
    public String matchItem(String[] matches) {
        for (String match: matches) {
            if (item.contains(match)) {
                return item;
            }
        }
        return null;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + item;
    }
}
