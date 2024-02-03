package task;

import task.Task;

/**
 * Represents a todo task.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo object with the given description.
     *
     * @param description description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo object.
     *
     * @return string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
