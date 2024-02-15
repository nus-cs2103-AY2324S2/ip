package lilybot;

/**
 * ToDo class, a task without start or end dtae.
 */
public class ToDo extends Task{
    protected String description;

    /**
     * Constructor of todo class.
     *
     * @param description The content of todos.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]"
                + super.toString();
    }
}
