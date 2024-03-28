package tasks;

/**
 * Represents a to-do task.
 * A to-do is a task without any date/time associated with it.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the specified description.
     *
     * @param description The description of the to-do task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the to-do task, including its type,
     * status, and description.
     *
     * @return A string representation of the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string formatted for file storage, including the task type,
     * completion status, and description.
     *
     * @return A string suitable for storing the task in a file.
     */
    public String toFileFormat() {
        String completed = this.isDone ? "1" : "0";
        return "T | " + completed + " | " + this.description;
    }
}
