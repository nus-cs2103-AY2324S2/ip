package duke.task;

/**
 * The to-do class represents a task without a date/time attached to it.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * Converts the task into a string format suitable for writing to a file.
     * @return The formatted string for writing to a file.
     */
    @Override
    public String toFileString() {
        return String.format("T | %d | %s", isDone ? 1 : 0, description);
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