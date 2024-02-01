package duke;

/**
 * Represents a ToDo task, which is a subclass of Task.
 */
public class ToDo extends Task {

    protected String by;

    /**
     * Constructs a ToDo object with the specified description.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo task.
     *
     * @return A string representation of the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
