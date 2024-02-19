package ghbot.task;

/**
 * Todo Class.
 * This class contains information about the todo task.
 */
public class Todo extends Task {

    /**
     * Todo Constructor.
     * @param description Description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string description for the item in the list for file operation.
     * @return A string that describes the item in the list for file operation.
     */
    @Override
    public String toFile() {
        return "T " + super.toFile();
    }

    /**
     * Returns a string description of a todo item.
     * @return A string that describes the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
