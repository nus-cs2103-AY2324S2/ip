package sam.task;

import sam.SamException;

/**
 * Represents a task.
 *
 */
public class Task {
    protected String description;
    protected boolean isDone;
    /**
     * Constructs a Task with the specified description.
     *
     * Initializes a Task with the given description. If the description is blank,
     * throws a SamException with a message indicating the need to provide a task description.
     *
     * @param description the description of the Task
     * @throws SamException if the provided description is blank
     */
    public Task(String description) throws SamException {
        if (description.isBlank()) {
            throw new SamException("Please provide a task description.");
        }
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves the status icon for the task.
     *
     * Returns a string representing the status of the task. If the task is done, returns "[X]";
     * otherwise, returns "[ ]".
     *
     * @return a string representing the status icon of the task
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Marks the task as done.
     *
     * @throws SamException If the task was already marked.
     */
    public void markAsDone() throws SamException {
        if (this.isDone) {
            throw new SamException("This task was already marked.");
        }
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     *
     * @throws SamException If the task was already unmarked.
     */
    public void markAsUndone() throws SamException {
        if (!this.isDone) {
            throw new SamException("This task was already unmarked.");
        }
        this.isDone = false;
    }

    public boolean containsKeyword(String keyword) {
        return description.contains(keyword);
    }

    public String toString() {
        return (this.getStatusIcon() + " " + this.description);
    }

    public String toFileFormat() {
        return null;
    }
}
