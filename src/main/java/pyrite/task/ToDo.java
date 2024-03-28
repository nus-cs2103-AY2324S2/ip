package pyrite.task;

/**
 * Task with only a description.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo.
     *
     * @param description Description of the task.
     */
    public ToDo(String description) {
        super(description);
    }
    /**
     * Generate a string representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
