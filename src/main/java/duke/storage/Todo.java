package duke.storage;

/**
 * The Todo class defines a 'ToDo' task used for the application
 *
 * @author Ryan NgWH
 */
public class Todo extends Task {
    /**
     * Create a Todo task
     *
     * @param description Description of the todo task
     */
    public Todo(String description) {
        super(description, TaskType.TODO, false);
    }

    /**
     * Create a Todo task
     *
     * @param description Description of the todo task
     * @param isDone      Status of the task
     */
    public Todo(String description, boolean isDone) {
        super(description, TaskType.TODO, isDone);
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
