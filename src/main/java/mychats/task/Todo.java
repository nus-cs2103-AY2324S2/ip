package duke.task;

/**
 * Represents a task with a description and without a date or time attached to it.
 */
public class Todo extends Task {

    /**
     * Constructs a to-do instance with the given description.
     *
     * @param description Description of the to-do task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Converts the task into a string format suitable for writing to a file.
     *
     * @return Formatted string for writing to a file.
     */
    @Override
    public String toFileString() {
        return String.format("T | %d | %s", isDone ? 1 : 0, description);
    }

    /**
     * Returns a formatted string representation of the to-do task.
     *
     * @return Formatted string representation of the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}