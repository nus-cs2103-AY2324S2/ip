package bob;

import java.util.UUID;

/**
 * Base class representing a user TODO task.
 */
public class Task {

    protected String description;
    protected boolean done = false;

    protected String uuid;

    /**
     * Constructor of Task.
     *
     * @param description Description of the task.
     */
    public Task(String description) {

        assert !description.trim().isEmpty(); // description should not be empty.

        this.description = description;
        this.uuid = UUID.randomUUID().toString();
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
        this.done = state;
        return this;
    }

    @Override
    public String toString() {
        return this.description;
    }

    public String toSavableFormat() {
        return this.uuid + "|T|" + this.description + "|" + this.done;
    }

    public String getStatus() {
        return "[" + (done ? "X" : " ") + "]";
    }

    public String getType() {
        return "[T]";
    }
}
