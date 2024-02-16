package duke.task;

/**
 * Represents a generic task.
 *
 * <p>The {@code Task} class is an abstract class representing a generic task. It contains
 * properties and methods common to all types of tasks, such as descriptions and status (done or
 * undone).</p>
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected final TaskType type;

    /**
     * Constructs a task with the given description and type.
     *
     * @param description The description of the task.
     * @param type        The type of the task, represented by {@link TaskType}.
     */
    public Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markUndone() {
        isDone = false;
    }

    public abstract String storageString();

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", description);
    }
}
