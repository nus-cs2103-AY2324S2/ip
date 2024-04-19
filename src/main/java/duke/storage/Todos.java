package duke.storage;

/**
 * The Todos class represents a todo task in the Duke task manager, which is a subtype of the Task class.
 * It inherits properties and methods from the Task class and provides a specific implementation for todo tasks.
 */
public class Todos extends Task {
    /**
     * Constructs a Todos object with the specified original command and description.
     *
     * @param originalCommand The original command used to create the todo task.
     * @param description     The description of the todo task.
     */
    public Todos(String originalCommand, String description) {
        super(originalCommand, description);
    }

    /**
     * Returns a string representation of the todo task, including its specific type identifier and the result
     * of the superclass's toString method.
     *
     * @return A string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
