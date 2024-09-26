package lai.task;

/**
 * Represents a to-do item, which is a basic form of task.
 */
public class ToDo extends Task {
    /**
     * Constructs a new to-do item with the specified description.
     * The to-do item is initially marked as not done.
     *
     * @param description The description of the to-do item.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a new to-do item with the specified description and initial completion status.
     *
     * @param description The description of the to-do item.
     * @param isDone The initial completion status of the to-do item. True if the to-do item is initially marked as done, false otherwise.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns a string representation of the to-do item, including its type and description.
     * The completion status is represented by "X" if the to-do item is done, or a space character otherwise.
     *
     * @return A string in the format "[T][completion_status] description", where completion_status is "X" if the to-do
     * item is done, or a space character otherwise.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
