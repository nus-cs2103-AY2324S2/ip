package task;

/**
 * Class for Todo object.
 */
public class Todo extends Task {
    /**
     * Constructor for a Todo object.
     * @param msg Description of the Todo task.
     */
    public Todo(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
