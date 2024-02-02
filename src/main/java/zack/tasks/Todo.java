package zack.tasks;

/**
 * Represents a todo task without a specific deadline or event time.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo task with the specified description and completion status.
     *
     * @param description The description of the todo task.
     * @param isDone      True if the todo task is marked as done, false otherwise.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string in a specific format for saving to a file.
     *
     * @return A string representation of the Todo task for saving to a file.
     */
    @Override
    public String toFileString() {
        return super.toFileString();
    }
}
