package academicweapon.task;

/**
 * Represents a Todo task in the Duke application.
 * The Todo class extends the Task class and does not have a specific deadline or event time.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the given description.
     *
     * @param description The description of the todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task.
     * Overrides the toString ethod in the Task class.
     *
     * @return String representation of the Todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a formatted string suitable for storing the Todo task in a file.
     * Overrides the fileToString method in the Task class.
     *
     * @return Formatted string for storing the Todo task in a file
     */
    @Override
    public String fileToString() {
        return "T | " + super.fileToString();
    }
}
