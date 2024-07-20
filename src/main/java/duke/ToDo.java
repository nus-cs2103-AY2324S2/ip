package duke;

/**
 * Represents a to-do task in the task list.
 * Subclass of the Task class.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo object with the given description.
     *
     * @param description The description of the to-do task.
     */
    public ToDo(String description) {
        super(description, Task.TaskType.TODO);

    }
    /**
     * Returns a string representation of the ToDo object.
     *
     * @return A string representation of the ToDo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    /**
     * Returns the type of the task (to-do).
     *
     * @return The type of the task.
     */
    @Override
    public String getType() {
        return "T";
    }
}
