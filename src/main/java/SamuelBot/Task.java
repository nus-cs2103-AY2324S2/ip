package SamuelBot;

/**
 * The Task class represents a task with a description and a status (done or not done).
 * This is an abstract class and must be subclassed to define specific types of tasks.
 */
public abstract class Task {
    String description;
    boolean isDone;

    /**
     * Constructs a new task with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the type of the task.
     *
     * @return The type of the task.
     */
    public abstract String getTaskType();

    /**
     * Returns a string representation of the task in a format suitable for storing in a file.
     *
     * @return A string representation of the task for file storage.
     */
    public abstract String toFileString();

    /**
     * Returns a string representation of the task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getTaskType() + "]" + (isDone ? "[X]" : "[ ]") + " " + description;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public abstract String getDescription();

    /**
     * Checks if the task is done.
     *
     * @return true if the task is done, false otherwise.
     */
    public abstract boolean isDone();
}
