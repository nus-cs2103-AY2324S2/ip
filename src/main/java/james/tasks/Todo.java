package james.tasks;

/**
 * Represents a todo task.
 */
public class Todo extends Task {
    /**
     * Constructor for a todo task.
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the todo task in a format suitable for saving to a file.
     *
     * @return Todo task in a format suitable for saving to a file.
     */
    @Override
    public String toFileFormat() {
        return "T | " + super.toFileFormat();
    }

    /**
     * Returns the string representation of the todo task.
     *
     * @return String representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
