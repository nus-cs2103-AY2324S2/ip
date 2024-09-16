package tony.tasks;

/**
 * The Todo class represents a simple task without a specific deadline or time frame.
 */
public class Todo extends Task {
    /**
     * Creates a new Todo task with the specified description.
     *
     * @param description The description of the todo task.
     * @throws IllegalArgumentException if the description is empty.
     */
    public Todo(String description) {
        super(description);
        this.type = TaskType.TODO;
        if (description.equals("")) {
            throw new IllegalArgumentException("Should have a description dawg");
        }

    }

    /**
     * Returns a string representation of the Todo task for display purposes.
     *
     * @return A formatted string representing the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a formatted string representation of the Todo task for storage purposes.
     *
     * @return A formatted string representing the Todo task.
     */
    @Override
    public String formattedString() {
        return "T" + super.formattedString();
    }

    /**
     * Returns the type of the task as a string.
     *
     * @return The type of the task ("TODO").
     */
    @Override
    public String getType() {
        return type.toString();
    }
}
