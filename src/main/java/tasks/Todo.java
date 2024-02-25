package tasks;

/**
 * Creates Task for Todo.
 */
public class Todo extends Task {
    /**
     * Constructor for Todo task.
     *
     * @param description action to be made
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Overrides toString to print the todo task.
     *
     * @return task output
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
