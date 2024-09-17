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
     * Returns the Task Object as a String.
     *
     * @return String Representation of the Task Object.
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

    /**
     * Marks the task as done.
     */
    public void markTask() {
        this.isDone = true;
    }

    /**
     * Unmarks the task as not done.
     */
    public void unmarkTask() {
        this.isDone = false;
    }

    /**
     * Abstract updateTaskDescription method for child task classes.
     *
     * @param field field to be updated.
     * @param updatedDescription description to be updated to.
     * @throws DukeException
     */
    public abstract void updateTaskDescription(String field, String updatedDescription) throws DukeException;

    /**
     * Returns the string representation of the description of the task.
     *
     * @return String representation of the description of this task.
     */
    public String getDescription() {
        return this.description;
    }
}
