package eggy.task;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo.
     *
     * @param name Name of the Todo.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Constructs a Todo.
     *
     * @param name Name of the Todo.
     * @param isDone Whether the Todo is done.
     */
    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    /**
     * Returns the string representation of the Todo.
     *
     * @return String representation of the Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation of the Todo to be saved in a file.
     *
     * @return String representation of the Todo to be saved in a file.
     */
    @Override
    public String toFileString() {
        return "T | " + super.toFileString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Todo) {
            Todo todo = (Todo) obj;
            return this.name.equals(todo.name);
        }
        return false;
    }
}
