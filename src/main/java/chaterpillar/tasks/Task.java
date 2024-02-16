package chaterpillar.tasks;

import chaterpillar.datetime.DateTime;
import chaterpillar.exceptions.ChaterpillarException;

/**
 * Represents a task. A <code>tasks.Task</code> object contains its
 * description or name, represented by a String, and a boolean
 * indicating whether the task is marked.
 */
public class Task {
    private boolean isMarked;
    private String taskName;
    private boolean hasDate;

    /**
     * Basic constructor
     *
     * @param taskName name of task to be tracked
     */
    public Task(String taskName) {
        this.taskName = taskName.trim();
        this.isMarked = false;
        this.hasDate = false;
    }

    /**
     * Overloaded constructor with marked status
     *
     * @param taskName name of task to be tracked
     * @param isMarked status of task (marked or unmarked)
     */
    public Task(String taskName, Boolean isMarked) {
        this.taskName = taskName;
        this.isMarked = isMarked;
        this.hasDate = false;
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
     * Sets this task to have date, by setting the
     * boolean flag to be true.
     */
    public void setHasDate() {
        this.hasDate = true;
    }

    /**
     * Gets the hasDate status.
     */
    public boolean getHasDate() {
        return this.hasDate;
    }

    public void updateName(String updatedName) {
        if (!updatedName.isBlank()) {
            this.taskName = updatedName;
        }
    }

    public void updateDate(String updatedDate) throws ChaterpillarException {
        if (!updatedDate.isBlank()) {
            throw new ChaterpillarException("This task type does not contain a date.");
        }
    }

    public void updateStartDate(String updatedStartDate) throws ChaterpillarException {
        if (!updatedStartDate.isBlank()) {
            throw new ChaterpillarException("This task type does not contain a start or end date.");
        }
    }

    public void updateEndDate(String updatedEndDate) throws ChaterpillarException {
        if (!updatedEndDate.isBlank()) {
            throw new ChaterpillarException("This task type does not contain a start or end date.");
        }
    }

    /**
     * Returns a formatted string meant for saving into the
     * text file.
     *
     * @return formatted String, consisting of isMarked status and taskname
     */
    public String formatStringForSaving() {
        return this.isMarked + "|" + this.taskName;
    }

    /**
     * checks if Task is within the date specified.
     *
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
