package alpa.tasks;

/**
 * Represents a task with a description and completion status.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    private TaskType type;

    /**
     * Constructs a task with the given description and type.
     * The task is initially not done.
     *
     * @param description the description of the task
     * @param type the type of the task
     */
    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    /**
     * Returns the type of the task.
     *
     * @return the type of the task
     */
    public TaskType getType() {
        return type;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return the status icon of the task
     */
    public String getStatusIcon() {
        return (isDone ? "[ X ]" : "[   ]");
    }

    /**
     * Returns the task in file format.
     *
     * @return the task in file format
     */
    public String toFileFormat() {
        String status = isDone ? "1" : "0";
        return getType().getShortName() + " | " + status + " | " + description;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
