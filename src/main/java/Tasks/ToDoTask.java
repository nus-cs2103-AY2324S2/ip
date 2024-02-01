package Tasks;

/**
 * The ToDoTask class represents a to-do task.
 */
public class ToDoTask extends Task {

    /**
     * Constructs a ToDoTask object with the specified task description.
     *
     * @param task The description of the to-do task.
     */
    public ToDoTask(String task) {
        super(task);
    }

    /**
     * Returns a string representation of the to-do task.
     *
     * @return A string representation of the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the to-do task for logging purposes.
     *
     * @return A string representation of the to-do task for logging purposes.
     */
    @Override
    public String logString() {
        return 'T' + super.logString();
    }
}
