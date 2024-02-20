package shirmin;

/**
 * Represents a basic task without any additional date/time.
 * <p>
 * Each Todo object consists only of a description.
 */
public class Todo extends Task{

    /**
     * Initializes a new Todo object with a description.
     *
     * @param description The description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task.
     * <p>
     * The format includes the type of task ('[T]') and the description.
     *
     * @return String representing the Todo task with its status and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}