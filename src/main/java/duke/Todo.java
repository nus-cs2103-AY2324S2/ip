package duke;

/**
 * Represents a todo task.
 * Extends the abstract base class Task.
 */
public class Todo extends Task {
    /**
     * Initializes a new Todo task with the given description.
     *
     * @param description The description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo task,
     * including its status icon and description.
     *
     * @return A string representing the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the Todo task for saving,
     * including task type, completion status, and description.
     *
     * @return A string representing the Todo task for saving in text file.
     */
    @Override
    public String toSave() {
        return "T | " + (super.isDone ? "1" : "0") + " | " + super.description;
    }
}
