/**
 * Represents a ToDo Task.
 * <p>
 * This class is the representation of a Todo task.
 * It extends from its parent class the Task class.
 */
public class ToDo extends Task {

    /**
     * Creates a ToDo object.
     * Will call the super constructor with the task name variable.
     *
     * @param c The name of the task.
     */
    public ToDo(String c) {
        super(c);
    }

    /**
     * Returns a string representation of this ToDo.
     * This includes an indicator that this is a ToDo object.
     * 
     * @return a string representation of this ToDo object.
     */
    @Override
    public String toString() {
        String s = "[T]" + super.toString();
        return s;
    }
}
