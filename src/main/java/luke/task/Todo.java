package luke.task;

/**
 * Represents a task with a description only.
 */
public class Todo extends Task {
    /**
     * Constructor for Todo, which is a task with a description only.
     *
     * @param description Description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the task in the format to be saved in the data file.
     *
     * @return String representation of the task in the format to be saved in the data file.
     */
    @Override
    public String toDataString() {
        return "T|" + super.toDataString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
