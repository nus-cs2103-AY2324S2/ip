/**
 * ToDo task class
 */
public class ToDo extends Task {

    /**
     * Constructor of todo class
     * @param description to describe the todo task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * String representation of ToDo task
     * @return Stirng representation of ToDo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
