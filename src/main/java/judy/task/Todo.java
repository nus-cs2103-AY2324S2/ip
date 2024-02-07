package judy.task;

/**
 * This class represents a todo task
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return " [T]" + super.toString();
    }

}
