package tasks;
/**
 * Represents a Todo task in a task management application.
 * A Todo task is a basic task with a description.
 *
 * @author Muhammad Rizki Bayuaji
 */
public class Todo extends Task {
    /**
     * Constructs a new Todo instance with the specified description.
     *
     * @param description The description of the Todo task.
     *
     * @author Muhammad Rizki Bayuaji
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task,
     * including the type indicator and description.
     *
     * @return A string representation of the Todo task.
     *
     * @author Muhammad Rizki Bayuaji
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
