/**
 * ToDo task class.
 */
public class ToDo extends Task {

    /**
     * Constructor of todo class.
     *
     * @param description to describe the todo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructor of todo class.
     *
     * @param description to describe the todo task.
     * @param isDone task isDone or not done.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * String representation of ToDo task.
     *
     * @return String representation of ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * String representation for storage.
     *
     * @return String representation for storage of ToDo task.
     */
    @Override
    public String toStorageString() {
        return CommandType.TODO.toString() + " , " + super.toStorageString();
    }

}
