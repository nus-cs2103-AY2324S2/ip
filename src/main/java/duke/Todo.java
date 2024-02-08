package duke;

/**
 * Represents a task of type Todo
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo.
     *
     * @param name The Content of the Todo
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * {@inheritDoc}
     * Specifies the type of the task.
     */
    @Override
    String taskToLine() {
        return "T | " + super.taskToLine();
    }

    /**
     * {@inheritDoc}
     * Specifies the type of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
