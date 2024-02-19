package judy.task;

/**
 * This Todo class represents a todo task.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo object with the specified description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task.
     *
     * @return The formatted string representation of the Todo task.
     */
    @Override
    public String toString() {
        return " [T]" + super.toString();
    }

    /**
     * Encodes the Todo task as a string for storage.
     *
     * @return The encoded string representation of the Todo task.
     */
    @Override
    public String encode() {
        return " T" + super.encode();
    }
}
