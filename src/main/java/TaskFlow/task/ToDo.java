package TaskFlow.task;

/**
 * Represents a todo task in the Duke chatbot application.
 */
public class ToDo extends Task {

    /**
     * Constructs a todo task with the given description.
     *
     * @param description The description of the todo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task.
     *
     * @return The string representation of the todo task.
     */
    @Override
    public String toString() {
        return "T" + " | " + super.toString();
    }
}
