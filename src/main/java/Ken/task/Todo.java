package ken.task;

/**
 * The Todo class represents a task with a deadline.
 *
 * It extends the Task class and includes additional information about the todo.
 */
public class Todo extends Task {

    /**
     * Constructs a todo task with the given description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task.
     *
     * @return A string representation of the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the todo task for file storage.
     *
     * @return A string representation of the todo task for file storage.
     */
    @Override
    public String toFileString() {
        return "T" + super.toFileString();
    }

}
