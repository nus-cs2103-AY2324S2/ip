package adam.task;

/**
 * {@inheritDoc}
 */
public class Todo extends Task {
    /**
     * {@inheritDoc}
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * {@inheritDoc}
     *
     * @return The string format of the task.
     */
    @Override
    public String toFileString() {
        return "T," + super.toFileString();
    }

    /**
     * {@inheritDoc}
     *
     * @return The string format of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }


}
