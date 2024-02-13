package duke.task;

import duke.exception.DukeException;

/**
 * Represents a task, which is a base class for other tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description The description of the task.
     */
    public Task(String description) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("The description of a task cannot be empty!");
        }
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task, either "X" or ""
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Marks the task as done or not done.
     *
     * @param isDone The status of the task.
     */
    public void markAsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Gets the full state of the task.
     * E.g. "T | 0 | read book"
     *
     * @return The description of the task.
     */
    public String getDataString() {
        return "";
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }
}
