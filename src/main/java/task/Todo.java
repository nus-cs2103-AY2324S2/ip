package task;

/**
 * Represents a task with a description.
 */
public class Todo extends Task {
    /**
     * Constructor for Todo.
     * @param description Description of the todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor for Todo.
     * @param description Description of the todo.
     * @param isDone Whether the todo is done.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the string representation of the todo.
     * @return String representation of the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
