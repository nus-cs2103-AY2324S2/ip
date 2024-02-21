package anita;

/**
 * The Todo class inherits from the Task class.
 * A type of task which can be done by the user.
 */
public class Todo extends Task {

    /**
     * The constructor for the Todo class.
     * Creates a Todo task.
     *
     * @param description The raw user input.
     */
    public Todo(String description, String status) {
        super(description, status);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
