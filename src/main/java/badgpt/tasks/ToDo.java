package badgpt.tasks;

/**
 * Representation of a task.
 */
public class ToDo extends Task {
    /**
     * Creates a new ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String saveTask() {
        return this.toString();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
