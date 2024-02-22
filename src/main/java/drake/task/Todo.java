package drake.task;

/**
 * Represents a Todo task with a description.
 * Inherits from the {@code Task} class and includes additional information
 */
public class Todo extends Task {

    /**
     * Initializes a todo task.
     *
     * @param description Description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Creates a string representation of an instance of this class.
     *
     * @return The String representation of an instance of this class.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
