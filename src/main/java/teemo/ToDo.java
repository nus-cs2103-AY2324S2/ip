package teemo;

/**
 * To Do task
 */
public class ToDo extends Task {

    /**
     * Constructor for To Do
     *
     * @param description Name of task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
