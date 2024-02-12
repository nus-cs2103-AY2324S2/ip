package task;

/**
 * A task with no due date.
 */
public class Todo extends Task {
    /**
     * Todo class constructor.
     * @param description The task description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Todo class constructor.
     * @param description The task description.
     * @param isDone A variable that determines whether a task has been completed.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Outputs the task as a string to the user.
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Outputs the task as a string to be stored in the save file.
     * @return A string representing the task to be saved.
     */
    @Override
    public String toFileString() {
        return "T" + super.toFileString();
    }

    /**
     * Gets the description of the task.
     * @return The task description.
     */
    @Override
    public String descriptionToString() {
        return super.descriptionToString();
    }
}
