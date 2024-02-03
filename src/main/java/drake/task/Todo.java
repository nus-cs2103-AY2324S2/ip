package drake.task;

/**
 * Represents a Todo task with a description.
 * Inherits from the {@code Task} class and includes additional information
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
