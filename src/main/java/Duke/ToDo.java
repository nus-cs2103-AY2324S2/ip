package duke;

/**
 * This class represents a ToDo task.
 * It extends the Task class with a specific string representation.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo task with the specified name.
     *
     * @param name the name of the ToDo task
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Returns a string representation of the ToDo task.
     * The returned string includes the task type ([T]) and the string representation of the superclass.
     *
     * @return a string representation of the ToDo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}