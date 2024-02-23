package henry.task;

import henry.HenryException;

/**
 * Represents a Todo Task.
 */
public class Todo extends Task {
    /**
     * Creates a Todo object.
     *
     * @param description The description of the todo.
     * @throws HenryException If the description is not specified.
     */
    public Todo(String description) throws HenryException {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
    @Override
    public String toFileString() {
        return String.format("T | %s", super.toFileString());
    }
}
