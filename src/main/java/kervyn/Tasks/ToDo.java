package kervyn.Tasks;

/**
 * Represents a ToDo task, which is a specific type of Task without additional date information.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with a description and status.
     *
     * @param description The description of the to-do task.
     * @param isCompleted The completion status of the to-do task.
     */
    public ToDo(String description, boolean isCompleted) {
        super(description, isCompleted, Type.TODO);
    }

    /**
     * Converts the task to a string representation, including type, status, and description.
     *
     * @return A string representation of the ToDO task.
     */
    @Override
    public String toString() {
        char check = this.getCompleted() ? 'X' : ' ';
        return "\t[" + this.getCapitalType() + "]" + " [" + check + "] " + this.getDescription();
    }
}
