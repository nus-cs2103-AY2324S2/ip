package henry.task;

import henry.HenryException;

/**
 * Represents a Task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for the Task class.
     *
     * @param description The description of the task.
     * @throws HenryException If the description is empty.
     */
    public Task(String description) throws HenryException {
        if (description.isBlank()) {
            throw new HenryException("No description of task!");
        }
        this.description = description;
        this.isDone = false;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the status icon of the task.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     *
     * @throws HenryException If the task was already marked.
     */
    public void markAsDone() throws HenryException {
        if (this.isDone) {
            throw new HenryException("This was already marked.");
        }
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     *
     * @throws HenryException If the task was already unmarked.
     */
    public void unmarkAsDone() throws HenryException {
        if (!this.isDone) {
            throw new HenryException("This was already unmarked.");
        }
        this.isDone = false;
    }

    public boolean containsKeyword(String keyword) {
        return description.contains(keyword);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    /**
     * Converts the task to a string to be stored in a file.
     *
     * @return The string to be stored in a file.
     */
    public String toFileString() {
        return String.format("%s | %s", isDone ? "1" : "0", description);
    }
}
