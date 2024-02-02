package task;

/**
 * Todo is a Task that has only description.
 */
public class Todo extends Task {

    /**
     * The constructor of Todo.
     *
     * @param description Description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}