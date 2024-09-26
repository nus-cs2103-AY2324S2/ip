package duke.task;

/**
 * The `Todo` class represents a task that have no specified time.
 * It provides methods to create strings related to task's details.
 * It extends the `Task` class.
 */
public class Todo extends Task {

    /**
     * Creates a `Todo` task with description.
     *
     * @param description The task's description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Creates a `Todo` task with description and status.
     *
     * @param description The task's description.
     * @param isDone The task's done status.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the formatted string containing done status and description of the task.
     *
     * @return The formatted string containing done status and description of the task.
     */
    @Override
    public String getDescriptionStatus() {
        return "[T]" + (isDone ? "[X] " : "[ ] ") + this.description;
    }

}
