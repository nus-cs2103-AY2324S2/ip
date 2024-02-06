package tasks;

/**
 * The ToDo class represents a task without any specific deadline or time requirement.
 * It extends the Task class and inherits its methods to manipulate and represent the task.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo object with the specified name.
     *
     * @param name The name or description of the ToDo task.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Returns a string representation of the ToDo task.
     *
     * @return A string containing the task's status icon and description, prefixed with "[T]".
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the ToDo task for saving to file.
     *
     * @return A string containing the task's status (0 for not done, 1 for done) and description, prefixed with "T".
     */
    @Override
    public String toStringForFile() {
        return "T" + super.toStringForFile();
    }
}
