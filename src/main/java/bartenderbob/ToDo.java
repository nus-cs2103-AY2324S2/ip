package bartenderbob;

/**
 * Represents the ToDo task that has a description.
 */
public class ToDo extends Task {
    /**
     * Creates an instance of a ToDo class that has a description.
     *
     * @param description Represents the descriptions of the task.
     */
    public ToDo(String description) {
        super(description);
    }
    /**
     * Creates an instance of a ToDo class that has a description and whether it has been completed.
     *
     * @param description Represents the descriptions of the task.
     * @param isDone Represents whether the task has been completed.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }
    /**
     * Returns the string representation of the ToDo task.
     *
     * @return String representation of the ToDo task.
     */
    @Override
    public String show() {
        super.status = isDone ? "X" : " ";
        return "[T]" + "[" + status + "]" + " " + this.description;
    }
}
