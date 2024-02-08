package univus.task;

/**
 * Represents a Todo task in the Univus application.
 */
public class ToDo extends Task {

    /**
     * Constructs a new instance of the ToDo class with the given description.
     *
     * @param description The description of the todo task.
     */
    public ToDo(String description) {
        super(description.replaceFirst("todo ", ""));
    }

    /**
     * Returns a string representation of the Todo task for display.
     *
     * @return A string containing the formatted representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
