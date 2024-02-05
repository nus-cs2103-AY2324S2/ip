package iggly.model;

/**
 * The {@link ToDo} class represents a todo task.
 */
public class ToDo extends Task {

    /**
     * Constructs a {@link ToDo} instance with the specified title.
     *
     * @param title The title of the ToDo task.
     */
    public ToDo(String title) {
        super(title);
    }

    /**
     * Returns a string representation of the ToDo task, including its type and title.
     *
     * @return A formatted string representing the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
