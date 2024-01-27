package duke.storage;

/**
 * The Todo class defines a 'ToDo' task used for the application
 *
 * @author Ryan NgWH
 */
public class Todo extends Task {
    /**
     * Constructor for a Todo object
     *
     * @param description Description of the todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * String representation of a Todo
     *
     * @return String representation of the Todo
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
