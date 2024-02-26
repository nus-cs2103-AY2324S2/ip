package task;

/**
 * This class is a child of class Task.
 * Todo handles the task where there is no date involved.
 */

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}