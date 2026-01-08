package scribbles.task;

/**
 * This class represents a to-do task.
 */
public class Todo extends Task {

    /**
     * Constructs a new to-do object with the specified description.
     *
     * @param description description of the to-do.
     * @param isCompleted true if to-do is completed.
     */
    public Todo(String description, boolean isCompleted) {
        super(description, isCompleted);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
