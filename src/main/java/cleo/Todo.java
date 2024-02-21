package cleo;

/**
 * Represents a simple "todo" task with only a description. Inherits from the Task class.
 */
public class Todo extends Task {
    /**
     * Constructor to create a Todo task.
     *
     * @param description The textual description of the Todo task.
     */
    Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation to visually identify a Todo task.
     *
     * @return The string "[T]"
     */
    @Override
    public String getTypeIcon() {
        return "[T]";
    }
}
