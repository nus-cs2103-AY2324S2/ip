package task;

/**
 * {@inheritDoc}
 */
public class ToDo extends Task {

    /**
     * {@inheritDoc}
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * {@inheritDoc}
     */
    public ToDo(boolean isDone, String taskName) {
        super(isDone, taskName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toData() {
        return "T | " + super.toData();
    }
}
