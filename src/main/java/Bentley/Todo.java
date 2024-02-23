package Bentley;

/**
 * Represents a task of type Todo, which is a generic task without a specific deadline or event time.
 * Extends the Task class.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo object with the specified description.
     *
     * @param description The description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a formatted string representation of the Todo object.
     *
     * @return A string containing the task type and description.
     */
    @Override
    public String toString() {
        return "T |" + super.toString();
    }
}
