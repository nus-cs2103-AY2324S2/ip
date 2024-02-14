package task;

/**
 * Represents a to-do task.
 * <p>
 * This class extends the {@link Task} class and is used to represent tasks that
 * have no specific deadline or start and end date. It provides a constructor to
 * create a new to-do task with a description.
 * </p>
 */
public class ToDo extends Task {

    /**
     * Constructs a new {@code ToDo} instance with the specified description.
     * 
     * @param description The description of the to-do task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returs the string representation of the to-do task.
     * 
     * @return The string representation of the to-do task.
     */
    @Override
    public String toString() {
        return super.getPriorityString() + "[T]" + super.toString();
    }

    /**
     * Returns the string representation of the to-do task to be saved in the file.
     * 
     * @return The string representation of the to-do task to be saved in the file.
     */
    @Override
    public String toFileString() {
        return "T | " + super.toFileString();
    }
}
