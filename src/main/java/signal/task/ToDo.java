package signal.task;

public class ToDo extends Task {

    /**
     * Constructor for a new ToDo task.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     *
     * @return The string representation of the ToDo task.
     */
    @Override
    public String toString() {
        return "T" + super.toString();
    }

    @Override
    public String checkType() {
        return "ToDo";
    }
}
