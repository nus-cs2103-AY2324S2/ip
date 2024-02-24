package headcube;
/**
 * Represents a to-do task. A ToDos object corresponds to a task
 * without any additional date or time attached to it.
 */
public class ToDos extends Task {
    /**
     * Constructor of ToDos object with the specified task description.
     *
     * @param description The description of the to-do task.
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the to-do task,
     * including its task type and status.
     *
     * @return A string representation of the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the file format representation of the to-do task,
     * useful for saving the task to a file.
     *
     * @return A string formatted for file saving.
     */
    @Override
    public String toFileFormat() {
        return "T | " + super.toFileFormat();
    }
}
