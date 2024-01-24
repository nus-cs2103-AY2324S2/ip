/**
 * The to-do class represents a task without a date/time attached to it.
 */
public class Todo extends Task {
    Todo(String description) {
        super(description);
    }

    /**
     * Returns a formatted string representation of the to-do task".
     *
     * @return The formatted string representation of the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}