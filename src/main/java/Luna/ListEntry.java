package Luna;

import java.time.LocalDate;

public class ListEntry {
    public static final String TYPE_TODO = "T";
    public static final String TYPE_DEADLINE = "D";
    public static final String TYPE_EVENT = "E";

    public enum taskType {
        T,
        D,
        E,

    }

    protected String task;
    protected boolean isDone;
    protected String type;

    protected LocalDate taskStart;

    protected LocalDate taskEnd;

    public ListEntry(String task, boolean isDone, String type, LocalDate tStart, LocalDate tEnd) {
        this.task = task;
        this.isDone = isDone;
        this.type = type;
        this.taskStart = tStart;
        this.taskEnd = tEnd;
    }

    public ListEntry(String task, boolean isDone, String type, LocalDate tEnd) {
        this.task = task;
        this.isDone = isDone;
        this.type = type;
        this.taskStart = null;
        this.taskEnd = tEnd;
    }

    public ListEntry(String task, boolean isDone, String type) {
        this.task = task;
        this.isDone = isDone;
        this.type = type;
        this.taskStart = null;
        this.taskEnd = null;
    }

    /**
     * Marks the check of the entry as true
     */
    public void markEntry () {
        this.isDone = true;
    }

    /**
     * Unmark the check of the entry as false
     */
    public void unmarkEntry() {
        this.isDone = false;
    }

    /**
     * Returns true if the task name contains the given keyword.
     * Ignores capitalisation.
     *
     * @param keyword string to compare
     * @return boolean whether contains the keyword
     */
    public boolean hasKeyword(String keyword) {
        return this.task.toLowerCase().contains(keyword.toLowerCase());
    }

    @Override
    public String toString() {
        return ("[" + this.type + "]" + (this.isDone ? "[X] " : "[ ] ") + this.task);
    }
}

