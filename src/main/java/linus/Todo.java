package linus;

/**
 * Represents the Todo Task.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo Task.
     *
     * @param name Name of the task.
     * @param isDone Whether the task is completed or not.
     */
    public Todo(String name, Boolean isDone) {
        super(name, isDone);
    }

    /**
     * Returns the output or String representation of the Todo Task.
     *
     * @return String representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}