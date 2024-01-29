package guanguan;

/**
 * Represents a Todo class inherited from Task.
 */
public class Todo extends Task {
    /**
     * Constructor for a Todo.
     *
     * @param description description of the todo
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String saveToText() {
        return String.format("T | %s | %s", this.isDone ? 1 : 0, this.description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
