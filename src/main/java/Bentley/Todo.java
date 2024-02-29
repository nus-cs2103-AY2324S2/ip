package bentley;

/**
 * The Todo class represents a specific type of task in the Bentley task
 * management application.
 * It extends the Task class and is used to handle tasks without specific
 * deadlines or events.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo object with the specified description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(" (T) " + description);
    }

    /**
     * Returns a string representation of the Todo object.
     * Overrides the toString method in the Task class.
     *
     * @return A formatted string containing the todo task description.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
