package talktomeorilldie;

/**
 * Represents a todo task.
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
     * Returns the string representation of the todo.
     * @return String representation of the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation of the todo for saving to file.
     * @return String representation of the todo for saving to file.
     */
    @Override
    public String toSaveString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
