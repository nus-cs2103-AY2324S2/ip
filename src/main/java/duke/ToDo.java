package duke;

/**
 * Represents a to-do task.
 */
public class ToDo extends Task {

    /**
     * Constructs a to-do task with the specified description.
     *
     * @param description The description of the to-do task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the to-do task.
     *
     * @return A string representing the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
