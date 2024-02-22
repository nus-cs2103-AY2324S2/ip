package podz.task;

/**
 * Represents a todo task in the task manager.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo object with the specified description.
     * 
     * @param description the description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the saved format of the todo task.
     * 
     * @return the saved format of the todo task.
     */
    @Override
    public String savedFormat() {
        return "T " + "|" + super.savedFormat();
    }

    /**
     * Returns a string representation of the todo task.
     * 
     * @return a string representing the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
