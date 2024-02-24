package ficin.task;

/**
 * The Todo class represents a task with no specific deadline or time.
 * It is a subclass of the Task class.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo object with a description.
     *
     * @param description A description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Converts the Todo object to a string that can be saved to a file.
     *
     * @return A formatted string representing the Todo object.
     */
    @Override
    public String toFileString() {
        // Format: T | [Status] | [Description]
        return "T | " + getStatusNumber() + " | " + description;
    }

    /**
     * Converts the Todo object to a user-friendly string representation.
     *
     * @return A string representing the Todo object in a user-friendly format.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
