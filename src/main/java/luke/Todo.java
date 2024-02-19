package luke;

/**
 * Represents a todo task in the task list.
 * <p>
 * A todo task is a simple task without any specific date or time associated with it.
 * </p>
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    protected String queryType() {
        return "Todo";
    }
}
