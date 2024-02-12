package task;

/**
 * Represents a todo task.
 */
public class Todo extends Task {
    private static final String TASK_CODE = "T";

    /**
     * Constructs a Todo object with the given description.
     *
     * @param description Description of the todo task.
     */
    public Todo(String description) {
        super(TASK_CODE, description);
    }

    /**
     * Returns a string representation of the Todo object.
     *
     * @return String representation of the todo task.
     */
    @Override
    public String toString() {
        return "[" + taskCode + "]" + super.toString();
    }
}
