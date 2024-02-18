package asher.tasks;

public class Task {
    protected String description;
    protected boolean isDone;
    private static int nextId = 1;
    private int id;

    /**
     * Represents a task.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.id = nextId++;
    }

    /**
     * Get the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retrieves the TaskId of the task.
     *
     * @return The TaskId of the task.
     */
    public int getTaskId() {
        return id;
    }

    /**
     * Retrieves the ID of the task.
     *
     * @return The ID of the task.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets the ID of the task.
     *
     * @param id The ID of the task that is to be set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the status of whether the task is marked.
     *
     * @return "X" if the task is mark, " " if the task is unmark
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Mark the task as completed.
     * Sets the 'isDone' flag to be true for marking the task.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Mark the task as not completed.
     * Sets the 'isDone' flag to be false for marking the task as not done.
     */
    public void markUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    public String writeToString() {
        return "";
    }
}
