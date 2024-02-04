package ben.tasks;

/**
 * Represents a todo task in the Ben task management application.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the specified attributes.
     *
     * @param isDone      Indicates if the task is done or not.
     * @param description The description of the todo task.
     */
    public Todo(boolean isDone, String description) {
        super(isDone, description);
    }

    /**
     * Converts the Todo task to a string representation for saving to a file.
     *
     * @return A string representing the Todo task for saving to a file.
     */

    @Override
    public String saveTask() {
        return "T | " + super.saveTask();
    }

    /**
     * Returns a string representation of the Todo task.
     *
     * @return A string representation of the Todo task.
     */

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
