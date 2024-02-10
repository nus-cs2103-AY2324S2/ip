package chaterpillar.tasks;

import chaterpillar.datetime.DateTime;

/**
 * Represents a task. A <code>tasks.Task</code> object contains its
 * description or name, represented by a String, and a boolean
 * indicating whether the task is marked.
 */
public class Task {
    private final String taskName;
    boolean isMarked;

    /**
     * Basic constructor
     * @param taskName name of task to be tracked
     */
    public Task(String taskName) {
        this.taskName = taskName.trim();
        this.isMarked = false;
    }

    /**
     * Overloaded constructor with marked status
     * @param taskName name of task to be tracked
     * @param isMarked status of task (marked or unmarked)
     */
    public Task(String taskName, Boolean isMarked) {
        this.taskName = taskName;
        this.isMarked = isMarked;
    }

    /**
     * Sets this task as marked, by setting the
     * boolean flag to be true.
     */
    public void mark() {
        this.isMarked = true;
    }

    /**
     * Sets this task as unmarked, by setting the
     * boolean flag to be false.
     */
    public void unmark() {
        this.isMarked = false;
    }

    /**
     * Returns a formatted string meant for saving into the
     * text file.
     * @return formatted String, consisting of isMarked status and taskname
     */
    public String stringForSaving() {
        return this.isMarked + "|" + this.taskName;
    }

    /**
     * checks if Task is within the date specified.
     * @param dt date specified
     * @return <code>false</code> by default.
     */
    public boolean isWithinDate(DateTime dt) {
        return false;
    }

    public boolean containsInName(String keyword) {
        return this.taskName.contains(keyword);
    }
    @Override
    public String toString() {
        return (this.isMarked ? "[X] " : "[ ] ") + this.taskName;
    }
}
