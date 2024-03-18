package solaire.data.task;

/**
 * A task to be finished with only a description.
 */
public class Todo extends Task {

    /**
     * Creates a todo task.
     *
     * @param taskName description of the todo task.
     */
    public Todo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
