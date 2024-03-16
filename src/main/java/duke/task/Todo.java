package duke.task;

/**
 * Represents a todo task that is added by the user.
 */
public class Todo extends Task {

    /**
     * {@inheritDoc}
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Changes the String representation of the todo to displaying a T letter,
     * along with its status icon and its description.
     * @return the specified string representation of the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
