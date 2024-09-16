package duke;

public class ToDo extends Task{

    /**
     * Constructs a ToDo task with a description.
     * @param description A string describing the to-do task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the ToDo task.
     *
     * @return A string representation of the ToDo task.
     */
    @Override
    public String toString() {
        return "T |" + super.toString().substring(1);
    }
}