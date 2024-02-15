package duke.task;


/**
 * Represents a to-do task that can be added to the task list.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the specified description.
     *
     * @param description The description of the to-do task.
     */
    public ToDo(String description) {
        super(description.isBlank() ? "Default Description" : description);
    }

    /**
     * Constructs a ToDo task with the specified description and completion status.
     *
     * @param description The description of the to-do task.
     * @param isDone      The completion status of the to-do task.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string representation of the ToDo task.
     *
     * @return A string containing the task type, status, and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the ToDo task for saving to a file.
     *
     * @return A formatted string for saving the ToDo task to a file.
     */
    @Override
    public String toFileString() {
        return "T | " + (getIsDone() ? "1" : "0") + " | " + getDescription();
    }
}
