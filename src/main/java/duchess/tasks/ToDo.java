package duchess.tasks;

public class ToDo extends Task {

    /**
     * Create new ToDo object.
     *
     * @param description description of Deadline Task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns String representation of object.
     *
     * @return String.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
