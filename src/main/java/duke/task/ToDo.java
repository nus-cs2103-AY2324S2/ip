package duke.task;

/**
 * Represents a to-do task that can be added to the task list.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the specified description. If the description is blank,
     * a default description is used. Ensures that the description provided is not null.
     *
     * @param description The description of the to-do task.
     * @throws IllegalArgumentException if the description is null.
     */
    public ToDo(String description) {
        // Validate the description is not null before passing to the superclass constructor.
        super(validateDescription(description));
    }

    /**
     * Constructs a ToDo task with the specified description and completion status.
     * Ensures that the description provided is not null.
     *
     * @param description The description of the to-do task.
     * @param isDone      The completion status of the to-do task.
     * @throws IllegalArgumentException if the description is null.
     */
    public ToDo(String description, boolean isDone) {
        // No need for assert here since validateDescription will throw an exception if needed.
        super(validateDescription(description), isDone);
    }

    /**
     * Validates the task's description. Throws an IllegalArgumentException if the description is null.
     * Returns a default description if provided description is blank.
     *
     * @param description The description to validate.
     * @return The validated description, or a default description if the original was blank.
     * @throws IllegalArgumentException if the description is null.
     */
    private static String validateDescription(String description) {
        if (description == null) {
            throw new IllegalArgumentException("Description cannot be null.");
        }
        return description.isBlank() ? "Default Description" : description;
    }

    /**
     * Returns a string representation of the ToDo task.
     *
     * @return A string containing the task type, status, and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the ToDo task for saving to a file.
     *
     * @return A formatted string for saving the ToDo task to a file.
     */
    @Override
    public String toFileString() {
        return "T | " + (isDone() ? "1" : "0") + " | " + getDescription();
    }
}
