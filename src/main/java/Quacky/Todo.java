package Quacky;

/**
 * Represents a to-do task in the Quacky application. A to-do task is a basic task
 * without a specific deadline or time frame.
 */
public class Todo extends Task {
    /**
     * Constructs a new Todo task with the specified description.
     *
     * @param description The textual description of the to-do task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    protected String toFileString() {
        return "T | " + super.toFileString();
    }
}
