package catchat;
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
    protected String getTaskTypeString() {
        return "T";
    }
    @Override
    protected TaskType getTaskType() {
        return TaskType.TODO;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
