package tasks;

/**
 * Represents a task without any deadline or start and end time.
 * Extends the Task class.
 */
public class Todo extends Task {

    /**
     * Constructs a new instance of a Todo object.
     * @param description The description of the todo task.
     * @param isDone Indicates whether the todo task has been completed.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
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
     * Returns a string representation of a todo task for saving to file.
     *
     * @return A string representing a todo task for saving to file.
     */
    @Override
    public String toFileString() {
        return "T|" + super.toFileString();
    }
}

