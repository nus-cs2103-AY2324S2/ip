package martin;

/**
 * Represents a todo task.
 */
public class Todo extends Task {

    /**
     * Constructs a todo task with the given description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task.
     *
     * @return The string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the todo task in a format suitable for saving to a file.
     *
     * @return The formatted string representation of the todo task.
     */
    @Override
    public String toFileString() {
        return "T | " + (this.getIsDone() ? "1" : "0") + " | " + this.getDescription();
    }
}
