package duke.task;

public class ToDo extends Task {
    /**
     * Constructs a ToDo Object.
     *
     * @param description String containing the description of the Task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the ToDo Object as a String.
     *
     * @return String Representation of the ToDo Object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
