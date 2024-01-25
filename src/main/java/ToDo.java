/**
 * Encapsulates TODO task. Inherits from Task.
 *
 * @author Tan Qin Yong
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo object with the given description.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo object.
     * Overrides the toString method in the Task class.
     *
     * @return A string representation of the ToDo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}