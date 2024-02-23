package cvb.tasks;

/**
 * Represents a task without a specific deadline or time period.
 * Extends the {@code Task} class and provides functionality specific to to-do tasks.
 */
public class ToDo extends Task {

    /**
     * Constructs a new {@code ToDo} instance with the specified description.
     *
     * @param description The description of the todo task.
     */
    public ToDo(String description) {
        this(description, false);
    }

    /**
     * Constructs a new {@code ToDo} instance with the specified description and completion status.
     *
     * @param description The description of the todo task.
     * @param isDone      The completion status of the todo task.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Converts the {@code ToDo} object to a string format suitable for saving to a file.
     *
     * @return A formatted string representing the to-do task for file storage.
     */
    @Override
    public String toFile() {
        return "T | " + super.toFile();
    }

    /**
     * Returns A string representation of the {@code ToDo} object.
     *
     * @return A formatted string representing the to-do task for display.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
