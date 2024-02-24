package hammy.task;

/**
 * The Task class represents a task with a description and a boolean indicating whether it is done.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructs a Task object with the given description.
     * The task is initialized as not done.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a Task object with the given description and status.
     *
     * @param description the description of the task
     * @param isDone      the status of the task, true if it is done, false otherwise
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public Boolean getStatus() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }
    /**
     * Marks the task as done by setting isDone to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone by setting isDone to false.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of the task.
     * The string contains the isDone and the description of the task.
     *
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }
}

