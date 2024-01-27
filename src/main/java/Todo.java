/**
 * Represents a "Todo" task. It is a simple task with only a description.
 */
public class Todo extends Task {

    /**
     * Constructs a todo task with the given description.
     *
     * @param description A string describing the todo task.
     */
    public Todo(String description) {
        super(description); // Call to the superclass (Task) constructor
    }

    /**
     * Returns a string representation of the todo task.
     * The method overrides the Task class's toString method to add the specific
     * identifier for Todo tasks.
     *
     * @return A formatted string representing the todo task,
     *         prefixed with "[T]" to denote a Todo type task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString(); // Adding the Todo identifier to the string representation
    }
}
