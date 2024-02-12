package bob;

import java.util.UUID;

/**
 * Base class representing a user TODO task.
 */
public class Task {

    protected String description;
    protected boolean isDone = false;

    protected String uuid;

    /**
     * Constructor of Task.
     *
     * @param description Description of the task.
     */
    public Task(String description) {

        assert !description.trim().isEmpty(); // description should not be empty.

        this.description = description;
        uuid = UUID.randomUUID().toString();
    }

    public Task setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    /**
     * Updates the status of a Task as done or undone.
     *
     * @param state If task is done or not.
     * @return a Task object that can be further manipulated.
     */
    public Task updateStatus(boolean state) {
        isDone = state;
        return this;
    }

    @Override
    public String toString() {
        return description;
    }

    public String toSavableFormat() {
        return uuid + "|T|" + description + "|" + isDone;
    }

    public String getStatus() {
        return "[" + (isDone ? "X" : " ") + "]";
    }

    public String getType() {
        return "[T]";
    }
}
