package tasks;

/**
 * Represents something that needs to be done by the user.
 */
public class Task {
    private final String description;
    private boolean status;

    /**
     * Creates a task object.
     *
     * @param description Indicates what the task is about.
     */
    public Task(String description) {
        this.description = description;
        this.status = false;
    }

    /**
     * Creates a task object.
     *
     * @param description Indicates what the task is about.
     * @param status Indicates whether the task has been completed or not.
     */
    public Task(String description, boolean status) {
        this.description = description;
        this.status = status;
    }

    /**
     * Works as a setter to set the task as completed or not.
     *
     * @param done Indicates whether the task has been completed or not.
     */
    public void setStatus(boolean done) {
        this.status = done;
    }

    /**
     * Provides a text version of the task that can be presented to the user.
     *
     * @return text version of the task
     */
    public String stringify() {
        String done = "[ ]";
        if (this.status) {
            done = "[X]";
        }
        return done + " " + this.description;
    }

    /**
     * Provides a text version of the task to be stored in the storage.
     *
     * @return text version of the task
     */
    public String toString() {
        String done = "O";
        if (this.status) {
            done = "X";
        }
        return done + " | " + this.description;
    }
}