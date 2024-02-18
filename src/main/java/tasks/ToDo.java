package tasks;

/**
 * Represents a todo task.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo object with the specified name.
     *
     * @param name the name of the todo task
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Returns a string representation of the ToDo object.
     *
     * @return a string containing the task type and name
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string formatted for writing to file.
     *
     * @return a string containing the task type and name in a specific format
     */
    @Override
    public String toWrite() {
        return "T | " + super.toWrite();
    }
}
