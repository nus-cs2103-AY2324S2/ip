package harvard.tasks;

/**
 * Represents a todo task, a simple task without any specific due date.
 * Extends the Task class.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo object with the specified description.
     *
     * @param description the description of the todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a Todo object with the specified description and completion status.
     *
     * @param description the description of the todo task
     * @param isDone      the completion status of the todo task
     */
    public Todo(String description, boolean isDone) {
        super(description);
        if (isDone) {
            super.mark();
        }
    }

    /**
     * Generates a string representation of the Todo object suitable for saving to a data file.
     *
     * @return a string representing the Todo object in a format suitable for saving
     */
    @Override
    public String saveString() {
        return "T," + (this.isDone ? "1," : "0,") + super.getDescription();
    }

    /**
     * Generates a string representation of the Todo object suitable for display to the user.
     *
     * @return a string representing the Todo object in a format suitable for display
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
