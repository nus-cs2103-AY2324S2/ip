package duke.task;

/**
 * Represents a to-do task without any specific deadline or time frame.
 * This class extends the Task abstract class, providing implementations for the to-do specific functionalities.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo instance with the specified description.
     *
     * @param description The description of the to-do task.
     */
    public ToDo(String description) {
        this.description = description;
    }

    /**
     * Returns a string representation of the to-do task, including its status and description.
     *
     * @return A string representation of the to-do task.
     */
    @Override
    public String toString() {
        return " [T]" + super.toString() + this.description;
    }

    /**
     * Returns a string representation of the to-do task suitable for saving to a file.
     * The saved format includes the task type, status, and description.
     *
     * @return A string suitable for saving the to-do task to a file.
     */
    @Override
    public String toSave() {
        return " T" + (super.getStatusIcon().equals("X") ? " | 1 | " : " | 0 | ") + this.description;
    }
}
