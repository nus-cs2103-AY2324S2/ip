package bob.task;

import java.time.LocalDate;

/**
 * Represents a task with a specified description. A <code>Task</code> object corresponds to
 * a task with a description.
 */
public abstract class Task {
    private static final String DONE_ICON = "X";
    private static final String NOT_DONE_ICON = " ";

    protected String description;
    protected boolean isDone;

    /**
     * Returns a task with a specified description.
     *
     * @param description The description for the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return The done icon if the task is done, and the not done icon otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? DONE_ICON : NOT_DONE_ICON);
    }

    /**
     * Getter for the <code>isDone</code> field.
     *
     * @return Whether the task is done.
     */
    public boolean getDone() {
        return isDone;
    }

    /**
     * Setter for the <code>isDone</code> field.
     *
     * @param isDone Whether the task is to be set as done or undone.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns storage format of the task.
     *
     * @return The string representation of the task in storage.
     */
    public String getStorageFormat() {
        return (isDone ? "true" : "false") + " | " + description;
    }

    /**
     * Returns whether the task is occurring on a specified day.
     *
     * @param date The date on which the task is said to occur on.
     * @return Whether the task is occurring on the specified date.
     */
    public boolean isOccurringOn(LocalDate date) {
        return false;
    }

    /**
     * Returns whether the task is due in a specified number of days.
     *
     * @param days The number of days in which the task is said to be due.
     * @return Whether the task is due in the specified number of days.
     */
    public boolean isDueIn(int days) {
        return false;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task to be displayed to the user.
     */
    @Override
    public String toString() {
        return '[' + getStatusIcon() + "] " + description;
    }
}
