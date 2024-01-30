package kervyn.Tasks;

/**
 * Represents a ToDo task, which is a specific type of Task without additional date information.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with a description and status.
     *
     * @param description The description of the to-do task.
     * @param status The completion status of the to-do task.
     */
    public ToDo(String description, boolean status) {
        super(description, status, Type.TODO);
    }
}
