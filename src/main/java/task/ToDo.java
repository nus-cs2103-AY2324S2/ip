package task;

/**
 * The ToDo class represents a task without a deadline in the EMIS application.
 * It is a subclass of the Task class and provides additional functionality specific to tasks without deadlines.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo object with the specified description.
     *
     * @param description The description of the todo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a new ToDo object with the specified completion status and description.
     *
     * @param isDone The completion status of the todo task.
     * @param description The description of the todo task.
     */
    public ToDo(boolean isDone, String description) {
        super(isDone, description);
    }

    /**
     * Returns a string representation of the ToDo object for storage purposes.
     *
     * @return A string representing the ToDo object in the format 'T | [Task]'.
     */
    @Override
    public String storeString() {
        return "T | " + super.storeString();
    }

    /**
     * Returns a string representation of the ToDo object for display purposes.
     *
     * @return A string representing the ToDo object in the format '[T][Task]'.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
