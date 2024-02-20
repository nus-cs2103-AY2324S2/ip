package tommy.task;

/**
 * Represents the task of type Todo.
 */
public class Todo extends Task {
    private static final String symbol = "[T]";

    /**
     * Constructor for a Todo task with its status as default not done.
     *
     * @param description Description of the deadline task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor for a Todo task with its status dependent on the input.
     * Used when retrieving tasks from the database.
     *
     * @param description Description of the todo task.
     * @param isDone Status of the todo task.
     */
    public Todo(String description, Boolean isDone) {
        super(description, isDone);
    }
    @Override
    public String toString() {
        return symbol + this.getStatusIcon() + this.description;
    }
}
