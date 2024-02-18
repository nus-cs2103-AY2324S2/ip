package cvb.tasks;

/**
 * The {@code ToDo} class represents a task without a specific deadline or time period.
 * It extends the {@code Task} class and provides functionality specific to to-do tasks.
 */
public class ToDo extends Task {

    /**
     * Constructs a new {@code ToDo} instance with the specified description.
     *
     * @param description the description of the to-do task
     */
    public ToDo(String description) {
        this(description, false);
    }

    /**
     * Constructs a new {@code ToDo} instance with the specified description and completion status.
     *
     * @param description the description of the to-do task
     * @param isDone      the completion status of the to-do task
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Converts the {@code ToDo} object to a string format suitable for saving to a file.
     *
     * @return a formatted string representing the to-do task for file storage
     */
    @Override
    public String toFile() {
        return "T | " + super.toFile();
    }

    /**
     * Returns a string representation of the {@code ToDo} object.
     *
     * @return a formatted string representing the to-do task for display
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
