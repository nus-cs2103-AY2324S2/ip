package capone.tasks;

/**
 * Represents a ToDo task.
 * Inherits from the Task class and provides specific implementation for ToDo tasks.
 *
 * @author Tay Rui-Jie
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo task with the specified description and completion status.
     *
     * @param description The description of the ToDo task.
     * @param status      The completion status of the ToDo task.
     */
    public ToDo(String description, boolean status) {
        super(TaskType.TODO, description, status);
    }

    /**
     * Overrides the toString method to represent the ToDo task as a string.
     *
     * @return A formatted string representation of the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
