package duke.tasks;

/**
 * Represent a Todo task.
 */
public class Todo extends Task {
    /**
     * Constructor for the deadline class
     *
     * @param description Description of the Todo.
     */
    public Todo(String description) {
        super(description, 'T');
    }
}
