package saopig.task;

/**
 * Represents a Todo task.
 */
public class Todo extends Task {

    /**
     * Creates a Todo task with the given description.
     *
     * @param description Description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string which displays the Todo task in FULL format.
     *
     * @return Todo task in string format.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
