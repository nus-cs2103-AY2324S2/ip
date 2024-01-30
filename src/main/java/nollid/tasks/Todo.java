package nollid.tasks;

/**
 * ToDo class represents a task without a specific deadline or duration.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
