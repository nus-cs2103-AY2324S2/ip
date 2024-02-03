package task;

/**
 * This class represents a to-do task.
 */
public class Todo extends Task {
    public Todo(String description, boolean isCompleted) {
        super(description, isCompleted);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
