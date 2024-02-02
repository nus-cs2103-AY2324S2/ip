package friday.task;

/**
 * Represents a todo task in the Friday application.
 * Subclass of the Task class.
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
     * Overrides the toString method to provide a formatted string representation of the Todo object.
     *
     * @return The formatted string representation of the Todo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
