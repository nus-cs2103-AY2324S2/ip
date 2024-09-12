package luna.entry;

import java.time.LocalDate;

/**
 * Represents an entry for each task. Each task have attributes, name, type, start, end and a check.
 */
public class ListEntry {
    public static final String TYPE_TODO = "T";
    public static final String TYPE_DEADLINE = "D";
    public static final String TYPE_EVENT = "E";

    /**
     * To verify it has a valid task type
     */
    public enum TaskType {
        T,
        D,
        E,

    }

    protected String task;
    protected boolean isDone;
    protected String type;

    protected LocalDate taskStart;

    protected LocalDate taskEnd;

    /**
     * An entry to represent the attributes of a task in the list
     *
     * @param task name of the task
     * @param isDone whether task is done
     * @param type the style of entry
     * @param tStart the start of the task
     * @param tEnd the end of the task
     */
    public ListEntry(String task, boolean isDone, String type, LocalDate tStart, LocalDate tEnd) {
        this.task = task;
        this.isDone = isDone;
        this.type = type;
        this.taskStart = tStart;
        this.taskEnd = tEnd;
    }

    /**
     * An entry to represent the attributes of a task in the list
     *
     * @param task name of the task
     * @param isDone whether task is done
     * @param type the style of entry
     * @param tEnd the end of the task
     */
    public ListEntry(String task, boolean isDone, String type, LocalDate tEnd) {
        this.task = task;
        this.isDone = isDone;
        this.type = type;
        this.taskStart = null;
        this.taskEnd = tEnd;
    }

    /**
     * An entry to represent the attributes of a task in the list
     *
     * @param task name of the task
     * @param isDone whether task is done
     * @param type the style of entry
     */
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
    public void markEntry() {
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

    public void snoozeEntry(int days) {

    }

    public String getTaskName() {
        return this.task;
    }

    public boolean getMark() {
        return this.isDone;
    }

    public String entryToFile() {
        return this.type + "," + this.isDone + "," + this.task + "," + this.taskStart + "," + this.taskEnd + "\n"; }

    public boolean isSnoozable() {
        return false;
    }
}

