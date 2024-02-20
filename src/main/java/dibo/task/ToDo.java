package dibo.task;

/**
 * The ToDo class represents a to-do task of the user.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo object with the specified parameters.
     *
     * @param description The String description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getSaveFormat() {
        return "todo | " + super.getSaveFormat();
    }
}
