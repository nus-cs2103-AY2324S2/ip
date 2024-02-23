package bozo;

/**
 * Represents a todo task.
 */
public class Todo extends Task {

    /**
     * Constructs a new todo task with the specified description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the todo task.
     *
     * @return string of todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation of the todo task to be saved in the file.
     *
     * @return string of todo task
     */
    @Override
    public String save() {
        return "T " + super.save();
    }
}
