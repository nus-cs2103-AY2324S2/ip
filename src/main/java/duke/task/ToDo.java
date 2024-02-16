package duke.task;

/**
 * {@inheritDoc}
 *
 * In this subclass we make a ToDo task without additional information.
 */
public class ToDo extends Task{
    /**
     * Constructs the class ToDo.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo (String description) {
        super(description);
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
    public String fileString() {
        return "T/" + super.fileString();
    }
}
