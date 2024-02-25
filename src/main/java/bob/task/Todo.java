package bob.task;

/**
 * Represents a todo with a specified description. A <code>Todo</code> object corresponds to
 * a todo with a description.
 */
public class Todo extends Task {
    public static final String STORAGE_INDICATOR = "T";

    /**
     * Returns a todo with a specified description.
     *
     * @param description The description for the todo.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns storage format of the todo.
     *
     * @return The string representation of the todo in storage.
     */
    @Override
    public String getStorageFormat() {
        return STORAGE_INDICATOR + " | " + super.getStorageFormat();
    }

    /**
     * Returns the string representation of the todo.
     *
     * @return The string representation of the todo to be displayed to the user.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
