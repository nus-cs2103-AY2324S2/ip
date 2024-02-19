package talkingjohn;

/**
 * Represents a todo task, which is a type of task with no specific deadline.
 */
public class Todo extends Task {

    /**
     * Constructs a todo task with the given description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
        assert description != null : "description cannot be null";
    }

    /**
     * Returns a string representation of the todo task.
     *
     * @return A string representing the todo task, including its completion status and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
