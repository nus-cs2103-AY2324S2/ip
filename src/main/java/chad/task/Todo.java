package chad.task;

/**
 * Represents a {@link Task} with no deadline or duration.
 */
public class Todo extends Task {
    /**
     * Constructor for a task.
     *
     * @param name name of task
     */
    public Todo(String name) {
        super(name);
    }

    public String getName() {
        return super.getName();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
