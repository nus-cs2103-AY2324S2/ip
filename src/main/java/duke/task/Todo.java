package duke.task;

/**
 * Represents a todo task.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the serialized string of this task. The serialized string will be
     * stored in the data file.
     *
     * @return The serialized string of the todo.
     */
    @Override
    public String serialize() {
        return "T | " + super.serialize();
    }
}
