package duke.task;

import duke.utility.DukeException;

/**
 * Class that represents a Task
 */
public abstract class Task {
    /** String containing the description of the Task. */
    protected String description;
    /** Boolean value that tells us if the Task has been marked done. */
    private boolean isDone;

    /**
     * Constructs a Task Object.
     *
     * @param description String containing the description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a String that indicated whether the Task has been marked done.
     *
     * @return String that indicates whether the Task has been marked done.
     */
    protected String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Returns the Task Object as a String
     *
     * @return String Representation of the Task Object.
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

    /**
     * Updates the Tasks status of whether it is marked done.
     *
     * @param isDone Boolean value indicating whether the Task should be marked done.
     */
    public void updateTaskStatus(boolean isDone) {
        if (isDone == true) {
            this.isDone = true;
        } else {
            isDone = false;
        }
    }

    public abstract void updateTaskDescription(String field, String updatedDescription) throws DukeException;

    public String getDescription() {
        return this.description;
    }
}
