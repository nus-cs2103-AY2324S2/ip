package duchess.task;

/**
 * ToDo class represents a task without any specific deadline or time.
 * It extends the Task class and provides methods to manipulate ToDo tasks.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo object with the given description.
     *
     * @param description the description of the ToDo task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a ToDo object with the given description and completion status.
     *
     * @param description the description of the ToDo task
     * @param isDone true if the task is completed, false otherwise
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string representation of the ToDo task.
     *
     * @return a string representation of the ToDo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the ToDo task in file format.
     *
     * @return a string representation of the ToDo task for file storage
     */
    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
