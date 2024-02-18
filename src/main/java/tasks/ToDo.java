package tasks;

import enums.TaskType;

/**
 * The ToDo class represents a task of type "ToDo" with a description.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo instance with the specified description.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
        this.type = TaskType.TODO;
    }

    /**
     * Converts the ToDo task to a string representation with task type and completion status.
     *
     * @return The string representation of the ToDo task with task type and completion status.
     */
    @Override
    public String toString() {
        return type.getSymbol() + super.toString();
    }
}
