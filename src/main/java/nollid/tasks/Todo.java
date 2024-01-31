package nollid.tasks;

/**
 * To-do class represents a task without a specific deadline or duration.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo object with the specified description.
     *
     * @param description The description of the to-do task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Overrides the toString method to provide a string representation of the Todo object.
     *
     * @return A formatted string representing the Todo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
