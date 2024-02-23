package dune.task;

/**
 * Represents a task. A task has a description and a boolean isDone status.
 */
public abstract class Task {

    /** String description of task */
    private String description;
    private boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description String description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for Task with isDone.
     *
     * @param description String description of task.
     * @param isDone Boolean isDone status of task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        String done = this.isDone ? "[X]": "[ ]";
        return done + " " + this.description;
    }
}
