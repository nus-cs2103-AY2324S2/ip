package duke;

public class Task {
    protected String DESCRIPTION;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description while assuming it is not done in the start.
     *
     * @param description A string representing the task's description.
     */
    public Task(String description) {
        this.DESCRIPTION = description;
        this.isDone = false;
    }

    /**
     * Returns a status icon based on whether the task is done.
     *
     * @return A string representing the task's completion status.
     */
    public String getStatusIcon() {
        return (isDone ? " 1 " : " 0 ");
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void mark_not_done() {
        isDone = false;
    }

    /**
     * Returns a string representation of the task, including its status and description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return  "|" + getStatusIcon() + "| " + this.DESCRIPTION;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task's description.
     */
    public String getDESCRIPTION() {
        return this.DESCRIPTION;
    }

    /**
     * Returns whether the task is done.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

}
