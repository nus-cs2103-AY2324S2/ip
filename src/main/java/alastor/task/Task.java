package alastor.task;

import alastor.AlastorException;

/**
 * Represents a task in the task list.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void mark() throws AlastorException {
        if (this.isDone) {
            throw new AlastorException("Task is already marked as done.");
        }
        this.isDone = true;
    }

    /**
     * Unmarks the task as done.
     */
    public void unmark() throws AlastorException {
        if (!this.isDone) {
            throw new AlastorException("Task is already marked as undone.");
        }
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return Status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    /**
     * Returns the string representation of the task to be saved in the file.
     *
     * @return String representation of the task to be saved in the file.
     */
    public String toFile() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            Task other = (Task) obj;
            return this.description.equals(other.description)
                    && this.isDone == other.isDone;
        }
        return false;
    }

    /**
     * Returns true if the task's description contains the keyword.
     *
     * @param keyword The keyword to search for.
     * @return True if the task's description contains the keyword.
     */
    public boolean isMatchingKeyword(String keyword) {
        return this.description.contains(keyword);
    }
}

