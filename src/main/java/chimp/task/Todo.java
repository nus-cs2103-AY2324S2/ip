package chimp.task;

/**
 * Represents a todo task.
 * Inherits from the Task class.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo object with the given text and status.
     * @param text The description of the todo task.
     * @param status The status of the todo task.
     */
    public Todo(String text, TaskStatus status) {
        super(text, status);
    }

    /**
     * Returns a string representation of the todo task.
     * @return A string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
