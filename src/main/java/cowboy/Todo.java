package cowboy;
/**
 * Todo class handles the todo tasks of the application
 */
public class Todo extends Task {
    /**
     * Constructor for Todo
     *
     * @param description
     */
    public Todo(String description) {
        super(description);
    }
    @Override
    protected String getTaskType() {
        return "T";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
